package de.balloncon.cedh_tool_backend.tournament.player;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.mapper.PlayerMapper;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.tournament.player.score.view.TournamentPlayerScoreView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TournamentPlayerService {

  @Autowired private final TournamentPlayerRepository tournamentPlayerRepository;
  @Autowired private final PlayerMapper playerMapper;

  // TODO: find a better way to declare these... db-table / cols related to tournament? properties?
  public static final BigDecimal STARTING_SCORE = new BigDecimal("1000.00");
  public static final BigDecimal WAGER_AMOUNT = new BigDecimal("0.07");
  public static final int NUMBER_OF_DIGITS_BEHIND_DECIMAL_POINT = 5;
  public static final BigDecimal SEAT_ONE_MULTIPLICATOR = new BigDecimal("1.32");
  public static final BigDecimal SEAT_TWO_MULTIPLICATOR = new BigDecimal("1.05");
  public static final BigDecimal SEAT_THREE_MULTIPLICATOR = new BigDecimal("0.91");
  public static final BigDecimal SEAT_FOUR_MULTIPLICATOR = new BigDecimal("0.72");
  public static final String DIVIDER_FOR_DRAW = "4.000";

  @Autowired
  private PodService podService;

  public TournamentPlayerService(TournamentPlayerRepository tournamentPlayerRepository, PlayerMapper playerMapper) {
    this.tournamentPlayerRepository = tournamentPlayerRepository;
    this.playerMapper = playerMapper;
  }

  public void save(TournamentPlayer tournamentPlayer) {
    tournamentPlayerRepository.save(tournamentPlayer);
  }

  public List<TournamentPlayerScoreView> getPlayerScoresByTournamentId(UUID tournamentId) {
    return tournamentPlayerRepository.findPlayerScoresByTournament(tournamentId);
  }

  public Optional<List<TournamentPlayer>> getTopTenByTournamentOrderByScoreDesc(
      UUID tournamentId, int cutSize) {
    return tournamentPlayerRepository.findTopTenByTournamentOrderByScoreDesc(
        tournamentId, PageRequest.of(0, cutSize));
  }

    public List<PlayerDto> getPlayersById(UUID tournamentId) {
        List<Player> tournamentPlayers = tournamentPlayerRepository.findByTournamentId(tournamentId);
        return tournamentPlayers.stream()
                .map(playerMapper::toDto)
                .collect(Collectors.toList());
    }

  public List<TournamentPlayer> calculatePlayerScoresAfterSwissRounds(UUID tournamentId, int rounds) {
    List<TournamentPlayer> tournamentPlayers =
            tournamentPlayerRepository.findByTournament(tournamentId);

    // prepare a map of players that can be referenced by their id
    HashMap<UUID, TournamentPlayer> tournamentPlayerMap = buildPlayerUuidMap(tournamentPlayers);

    // data store for the results of all permutations
    List<HashMap<UUID, TournamentPlayer>> playerScoresByPermutation = new ArrayList<>();

    // for reach permutation, calculate the point scores for all players
    List<List<Integer>> roundPermutations = generatePermutations(generateRoundList(rounds));
    for (List<Integer> roundPermutation : roundPermutations) {
      // create a clone of our map, because we change the score values
      HashMap<UUID, TournamentPlayer> tournamentPlayerMapForPermutation =
              cloneTournamentPlayerMap(tournamentPlayerMap);
      playerScoresByPermutation.add(tournamentPlayerMapForPermutation);

      // calculate score after each round
      for (Integer roundNumber : roundPermutation) {
        calculatePlayerScoreChangesForRound(tournamentId,
                tournamentPlayerMapForPermutation,
                roundNumber);
      }
    }

    // calculate average score per player
    calculateAverageThroughPermutations(playerScoresByPermutation, tournamentPlayers);

    // BYE adjustment
    adjustForByes(tournamentId, rounds, tournamentPlayers);

    return tournamentPlayers;
  }

  private void adjustForByes(UUID tournamentId, int rounds, List<TournamentPlayer> tournamentPlayers) {
    HashMap<UUID, Integer> amountOfByesPerPlayer = getByesPerPlayer(tournamentId);

    for (TournamentPlayer tournamentPlayer : tournamentPlayers) {
      if (tournamentPlayer.getScore().compareTo(STARTING_SCORE) == 1
              && amountOfByesPerPlayer.containsKey(tournamentPlayer.getPlayer().getId())) {
        addAverageScoreDiffToPlayer(rounds, tournamentPlayer, amountOfByesPerPlayer);
      }
    }
  }

  private static void addAverageScoreDiffToPlayer(int rounds, TournamentPlayer tournamentPlayer, HashMap<UUID, Integer> amountOfByesPerPlayer) {
    BigDecimal scoreDiff = tournamentPlayer.getScore().subtract(STARTING_SCORE);
    Integer amountOfByes = amountOfByesPerPlayer.get(tournamentPlayer.getPlayer().getId());

    BigDecimal scoreDiffPerRound = scoreDiff.divide(new BigDecimal(rounds - amountOfByes),
            NUMBER_OF_DIGITS_BEHIND_DECIMAL_POINT, RoundingMode.UP);
    BigDecimal newScore = tournamentPlayer.getScore().add(scoreDiffPerRound.multiply(new BigDecimal(amountOfByes)));
    tournamentPlayer.setScore(newScore);
  }

  private HashMap<UUID, Integer> getByesPerPlayer(UUID tournamentId) {
    HashMap<UUID, Integer> amountOfByesPerPlayer = new HashMap<>();
    for (Pod pod : podService.getPodsAndSeatsByTournamentId(tournamentId)) {
      for (Seat seat : pod.getSeats()) {
        if (Result.bye.equals(seat.getResult())) {
          int amountOfByes = amountOfByesPerPlayer.containsKey(seat.getPlayer().getId())
                  ? amountOfByesPerPlayer.get(seat.getPlayer().getId()) + 1
                  : 1;
          amountOfByesPerPlayer.put(seat.getPlayer().getId(), amountOfByes);
        }
      }
    }
    return amountOfByesPerPlayer;
  }

  private static void calculateAverageThroughPermutations(List<HashMap<UUID, TournamentPlayer>> playerScoresByPermutation, List<TournamentPlayer> tournamentPlayers) {
    Map<UUID, List<BigDecimal>> playerScores = new HashMap<>();
    extractPlayerScoresFromPermutations(playerScoresByPermutation, playerScores);

    for (TournamentPlayer tournamentPlayer : tournamentPlayers) {
      UUID playerId = tournamentPlayer.getPlayer().getId();
      List<BigDecimal> scores = playerScores.get(playerId);

      if (!scores.isEmpty()) {
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal score : scores) {
          sum = sum.add(score);
        }
        BigDecimal average = sum.divide(BigDecimal.valueOf(scores.size()), NUMBER_OF_DIGITS_BEHIND_DECIMAL_POINT,
                RoundingMode.UP);
        tournamentPlayer.setScore(average);
      } else {
        log.warn("No score found for player " + playerId);
        tournamentPlayer.setScore(BigDecimal.ZERO);
      }
    }
  }

  private static void extractPlayerScoresFromPermutations(List<HashMap<UUID, TournamentPlayer>> playerScoresByPermutation, Map<UUID, List<BigDecimal>> playerScores) {
    for(HashMap<UUID, TournamentPlayer> tournamentPlayer : playerScoresByPermutation) {
      for(Map.Entry<UUID, TournamentPlayer> entry : tournamentPlayer.entrySet()) {
        UUID playerId = entry.getKey();
        BigDecimal score = entry.getValue().getScore();

        // adds an entry for the player, if not present yet
        playerScores.computeIfAbsent(playerId, k -> new ArrayList<>()).add(score);
      }
    }
  }

  private static HashMap<UUID, TournamentPlayer> buildPlayerUuidMap(List<TournamentPlayer> tournamentPlayers) {
    HashMap<UUID, TournamentPlayer> tournamentPlayerMap = new HashMap<>();
    for (TournamentPlayer tournamentPlayer : tournamentPlayers) {
      // assign starting score to each player, to make sure the data from the DB does not affect the calculation
      tournamentPlayer.setScore(STARTING_SCORE);
      // then populate playerMap, so we can reference them via PlayerId
      tournamentPlayerMap.put(tournamentPlayer.getPlayer().getId(), tournamentPlayer);
    }
    return tournamentPlayerMap;
  }

  private static HashMap<UUID, TournamentPlayer> cloneTournamentPlayerMap(HashMap<UUID, TournamentPlayer> tournamentPlayerMap) {
    HashMap<UUID, TournamentPlayer> clonedMap = new HashMap<>();

    for (Map.Entry<UUID, TournamentPlayer> entry : tournamentPlayerMap.entrySet()) {
      clonedMap.put(entry.getKey(), entry.getValue().clone());
    }

    return clonedMap;
  }

  private void calculatePlayerScoreChangesForRound(UUID tournamentId,
                                                   HashMap<UUID, TournamentPlayer> tournamentPlayerMap,
                                                   int roundNumber) {
    List<Pod> podsByRoundNumber = podService.getPodsByRoundNumber(tournamentId, roundNumber);
    if (podsByRoundNumber.isEmpty()) {
      throw new RuntimeException("No pods found for round number " + roundNumber);
    }

    for (Pod pod : podsByRoundNumber) {
      // calculate points wagered
      BigDecimal potAmount = BigDecimal.ZERO;
      Result podResult = podService.getResult(pod);

      // point totals do not chance got BYE pods
      if (podResult == Result.win || podResult == Result.draw) {
        for (Seat seat : pod.getSeats()) {
          potAmount = potAmount.add(performHareruyaCalculationBySeat(tournamentPlayerMap.get(seat.getPlayer().getId()),
                  seat.getSeat()));
        }

        assignNewScoresToPod(tournamentPlayerMap, pod, potAmount);
      }
    }
  }

  private void assignNewScoresToPod(HashMap<UUID, TournamentPlayer> tournamentPlayersMap, Pod pod, BigDecimal potAmount) {
    Result podResult = podService.getResult(pod);

    switch (podResult) {
      case Result.win -> addPointsToPodWinner(tournamentPlayersMap, pod, potAmount);
      case Result.draw -> addPointsForDraw(tournamentPlayersMap, pod, potAmount);
    }
  }

  private void addPointsToPodWinner(HashMap<UUID, TournamentPlayer> tournamentPlayersMap, Pod pod, BigDecimal potAmount) {
    // Winner gets all wagered points. All other players do not get any points
    for (Seat seat : pod.getSeats()) {
      if (Result.win.equals(seat.getResult())) {
        BigDecimal winnerScore = tournamentPlayersMap.get(seat.getPlayer().getId()).getScore();
        winnerScore = winnerScore.add(potAmount);
        tournamentPlayersMap.get(seat.getPlayer().getId()).setScore(winnerScore);
      }
    }
  }

  private void addPointsForDraw(HashMap<UUID, TournamentPlayer> tournamentPlayersMap, Pod pod, BigDecimal potAmount) {
    // All players get 1/4 of the wagered points. Needs to be calculated because of the seat weighting
    BigDecimal pointsForEachPlayer = potAmount.divide(new BigDecimal("4.00"),
            NUMBER_OF_DIGITS_BEHIND_DECIMAL_POINT, RoundingMode.UP);

    for (Seat seat : pod.getSeats()) {
      BigDecimal playerScore = tournamentPlayersMap.get(seat.getPlayer().getId()).getScore();
      playerScore = playerScore.add(pointsForEachPlayer);
      tournamentPlayersMap.get(seat.getPlayer().getId()).setScore(playerScore);
    }
  }

  // TODO: probably not needed anymore
  public void calculateAndAssignNewScores(
      UUID tournamentId, HashMap<String, Integer> playerSeatMap, UUID winningPlayerId) {

    List<String> playerIds = new ArrayList<>(playerSeatMap.keySet());
    List<TournamentPlayer> tournamentPlayers =
        tournamentPlayerRepository.findByTournamentAndPlayers(tournamentId, playerIds);

    if (playerSeatMap.isEmpty()) {
      throw new IllegalStateException("No tournament players found for given pod");
    }

    BigDecimal totalContribution = deductPointsFromPlayers(playerSeatMap, tournamentPlayers);
    givePointsToPlayers(winningPlayerId, tournamentPlayers, totalContribution);
    tournamentPlayerRepository.saveAll(tournamentPlayers);
  }

  // TODO: probably not needed anymore
  private void givePointsToPlayers(
      UUID winningPlayerId,
      List<TournamentPlayer> tournamentPlayers,
      BigDecimal totalContribution) {
    // Step 2: Add the totalContribution to the winner
    boolean isWinningPlayer = winningPlayerId != null;
    BigDecimal divider = new BigDecimal(DIVIDER_FOR_DRAW);

    for (TournamentPlayer tournamentPlayer : tournamentPlayers) {
      // Case player won
      if (isWinningPlayer) {
        if (tournamentPlayer.getPlayer().getId().toString().equals(winningPlayerId.toString())) {
          tournamentPlayer.setScore(tournamentPlayer.getScore().add(totalContribution));
        }
        // case draw
      } else {
        tournamentPlayer.setScore(
            tournamentPlayer
                .getScore()
                .add(
                    (totalContribution.divide(
                        divider, NUMBER_OF_DIGITS_BEHIND_DECIMAL_POINT, RoundingMode.UP))));
      }
    }
  }


  private BigDecimal deductPointsFromPlayers(
      HashMap<String, Integer> playerSeatMap, List<TournamentPlayer> tournamentPlayers) {
    BigDecimal totalContribution = new BigDecimal("0.000");

    // Take 7% of the points of each player and add them to the "pot"
    for (TournamentPlayer tournamentPlayer : tournamentPlayers) {
      Integer seat = playerSeatMap.get(tournamentPlayer.getPlayer().getId().toString());
      totalContribution = totalContribution.add(performHareruyaCalculationBySeat(tournamentPlayer, seat));
    }
    return totalContribution;
  }


  private BigDecimal performHareruyaCalculationBySeat(TournamentPlayer tournamentPlayer, Integer seatPosition) {
    return switch (seatPosition) {
      case 1 -> performHareruyaCalculation(tournamentPlayer, SEAT_ONE_MULTIPLICATOR);
      case 2 -> performHareruyaCalculation(tournamentPlayer, SEAT_TWO_MULTIPLICATOR);
      case 3 -> performHareruyaCalculation(tournamentPlayer, SEAT_THREE_MULTIPLICATOR);
      case 4 -> performHareruyaCalculation(tournamentPlayer, SEAT_FOUR_MULTIPLICATOR);
      default -> throw new IllegalStateException("Invalid seat position: " + seatPosition);
    };
  }

  private BigDecimal performHareruyaCalculation(
      TournamentPlayer player, BigDecimal seatMultiplicator) {
    BigDecimal contribution =
        player
            .getScore()
            .multiply(WAGER_AMOUNT)
            .setScale(NUMBER_OF_DIGITS_BEHIND_DECIMAL_POINT, RoundingMode.UP)
            .multiply(seatMultiplicator);
    player.setScore(player.getScore().subtract(contribution));
    return contribution;
  }

  public List<List<Integer>> generatePermutations(List<Integer> numbers) {
    List<List<Integer>> permutations = new ArrayList<>();
    // Create a mutable copy of the input list
    List<Integer> mutableNumbers = new ArrayList<>(numbers);
    permute(mutableNumbers, 0, permutations);
    return permutations;
  }

  private void permute(List<Integer> numbers, int currentIndex, List<List<Integer>> permutations) {
    if (currentIndex == numbers.size() - 1) {
      permutations.add(new ArrayList<>(numbers));
      return;
    }

    for (int i = currentIndex; i < numbers.size(); i++) {
      Collections.swap(numbers, currentIndex, i);
      permute(numbers, currentIndex + 1, permutations);
      Collections.swap(numbers, currentIndex, i); // Backtrack
    }
  }

  private List<Integer> generateRoundList(int rounds) {
    List<Integer> roundList = new ArrayList<>();
    for (int i = 1; i <= rounds; i++) {
      roundList.add(i);
    }
    return roundList;
  }
}
