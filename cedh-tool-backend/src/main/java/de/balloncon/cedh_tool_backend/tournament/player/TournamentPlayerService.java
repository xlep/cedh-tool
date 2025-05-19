package de.balloncon.cedh_tool_backend.tournament.player;

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

@Slf4j
@Component
public class TournamentPlayerService {

  @Autowired private final TournamentPlayerRepository tournamentPlayerRepository;

  // TODO: find a better way to declare these... db-table / cols related to tournament? properties?
  private static final BigDecimal STARTING_SCORE = new BigDecimal("1500.00");
  private static final BigDecimal WAGER_AMOUNT = new BigDecimal("0.07");
  private static final int NUMBER_OF_DIGITS_BEHIND_DECIMAL_POINT = 3;
  private static final BigDecimal SEAT_ONE_MULTIPLICATOR = new BigDecimal("1.32");
  private static final BigDecimal SEAT_TWO_MULTIPLICATOR = new BigDecimal("1.05");
  private static final BigDecimal SEAT_THREE_MULTIPLICATOR = new BigDecimal("0.91");
  private static final BigDecimal SEAT_FOUR_MULTIPLICATOR = new BigDecimal("0.72");
  private static final String DIVIDER_FOR_DRAW = "4.000";

  @Autowired
  private PodService podService;

  public TournamentPlayerService(TournamentPlayerRepository tournamentPlayerRepository) {
    this.tournamentPlayerRepository = tournamentPlayerRepository;
  }

  public void save(TournamentPlayer tournamentPlayer) {
    tournamentPlayerRepository.save(tournamentPlayer);
  }

  public List<TournamentPlayerScoreView> getPlayerScoresByTournament(UUID tournamentId) {
    return tournamentPlayerRepository.findPlayerScoresByTournament(tournamentId);
  }

  public Optional<List<TournamentPlayer>> getTopTenByTournamentOrderByScoreDesc(
      UUID tournamentId, int cutSize) {
    return tournamentPlayerRepository.findTopTenByTournamentOrderByScoreDesc(
        tournamentId, PageRequest.of(0, cutSize));
  }


  public List<TournamentPlayer> calculatePlayerScores(UUID tournamentId) {
    List<TournamentPlayer> tournamentPlayers =
            tournamentPlayerRepository.findByTournament(tournamentId);
    HashMap<UUID, TournamentPlayer> tournamentPlayerMap = new HashMap<>();

    for (TournamentPlayer tournamentPlayer : tournamentPlayers) {
      // assign starting score to each player, to make sure the data from the DB does not affect the calculation
      tournamentPlayer.setScore(STARTING_SCORE);
      // then populate playerMap, so we can reference them via PlayerId
      tournamentPlayerMap.put(tournamentPlayer.getPlayer().getId(), tournamentPlayer);
    }

    // shuffle rounds
      // calculate all rounds
        // calculate for round

          // calculate for pod
           // assign change to player


    List<Pod> podsByRoundNumber = podService.getPodsByRoundNumber(tournamentId, 1);
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
        log.info(String.format("Total points in pot for pod %s are %s", pod.getId(), potAmount.toString()));

        assignNewScoresToPod(tournamentPlayerMap, pod, potAmount);
      }
    }

    return tournamentPlayers;
  }

  private void assignNewScoresToPod(HashMap<UUID, TournamentPlayer> tournamentPlayersMap, Pod pod, BigDecimal potAmount) {
    Result podResult = podService.getResult(pod);

    // TODO bye
    switch (podResult) {
      case Result.win -> addPointsToPodWinner(tournamentPlayersMap, pod, potAmount);
      case Result.draw -> addPointsForDraw(tournamentPlayersMap, pod, potAmount);
    }
  }

  // Winner gets all wagered points. All other players do not get any points
  private void addPointsToPodWinner(HashMap<UUID, TournamentPlayer> tournamentPlayersMap, Pod pod, BigDecimal potAmount) {
    for (Seat seat : pod.getSeats()) {
      if (Result.win.toString().equals(seat.getResult())) {
        BigDecimal winnerScore = tournamentPlayersMap.get(seat.getPlayer().getId()).getScore();
        winnerScore = winnerScore.add(potAmount);
        tournamentPlayersMap.get(seat.getPlayer().getId()).setScore(winnerScore);
      }
    }
  }

  // All players get 1/4 of the wagered points. Needs to be calculated because of the seat weighting
  private void addPointsForDraw(HashMap<UUID, TournamentPlayer> tournamentPlayersMap, Pod pod, BigDecimal potAmount) {
    BigDecimal pointsForEachPlayer = potAmount.divide(new BigDecimal("4.00"),
            NUMBER_OF_DIGITS_BEHIND_DECIMAL_POINT, RoundingMode.UP);

    for (Seat seat : pod.getSeats()) {
      BigDecimal playerScore = tournamentPlayersMap.get(seat.getPlayer().getId()).getScore();
      playerScore = playerScore.add(pointsForEachPlayer);
      tournamentPlayersMap.get(seat.getPlayer().getId()).setScore(playerScore);
    }
  }

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
    // Step 1: Deduct 7% from each player
    BigDecimal totalContribution = new BigDecimal("0.000");

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
}
