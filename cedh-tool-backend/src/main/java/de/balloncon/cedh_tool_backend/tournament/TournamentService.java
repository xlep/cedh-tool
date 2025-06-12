package de.balloncon.cedh_tool_backend.tournament;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.dto.RoundDto;
import de.balloncon.cedh_tool_backend.dto.RoundDto.PodDto;
import de.balloncon.cedh_tool_backend.dto.RoundDto.SeatDto;
import de.balloncon.cedh_tool_backend.mapper.PlayerMapper;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.pod.PodType;
import de.balloncon.cedh_tool_backend.score.ScoreService;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.seat.SeatService;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerId;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerService;
import de.balloncon.cedh_tool_backend.util.ResponseMessages;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TournamentService {

  @Autowired
  private TournamentRepository tournamentRepository;
  @Autowired
  private PodService podService;
  @Autowired
  private PlayerService playerService;
  @Autowired
  private SeatService seatService;
  @Autowired
  private ScoreService scoreService;
  @Autowired
  private TournamentPlayerService tournamentPlayerService;
  @Autowired
  private PlayerMapper playerMapper;

  private TournamentLogic tournamentLogic;

  private static final int POD_SIZE = 4;

  // TODO: these should not be fields of a Service class
  private final List<List<List<Integer>>> schedule = new ArrayList<>();
  private Set<String> seenPairs = new HashSet<>();
  private Map<Integer, Player> indexToPlayer = new HashMap<>();
  private Map<UUID, Integer> playerIdToIndex = new HashMap<>();
  private final static int TOP_TEN_CUT = 10;

  public void save(Tournament tournament) {
    tournamentRepository.save(tournament);
  }

  public RoundDto generateNextRound(UUID tournamentId) {
    Tournament tournament = tournamentRepository.findTournamentById(tournamentId);

    switch (tournament.getMode()) {
      case HARERUYA:
        this.tournamentLogic = new TournamentServiceHareruya();
        break;
      case SWISS:
        this.tournamentLogic = new TournamentServiceSwiss();
        break;
    }

    return tournamentLogic.generateNextRound(tournamentId);
  }

  private List<PodDto> adjustForTableLocks(Tournament tournament, List<PodDto> podDtos) {
    // TODO: using the DTO classes feels clunky in general. Why are using these for the internal logic around round generation?
    // TODO: additional note; if we can refactor the pod generation so the pods only get persisted when everyhing is prepared, we can make this proces much cleaner
    Map<UUID, TournamentPlayer> playersWithTableLock = tournamentPlayerService
        .getPlayersForTournament(tournament.getId())
        .stream()
        .filter(player -> player.getTableLock() != null)
        .collect(Collectors.toMap(player -> player.getPlayer().getId(), player -> player));

    for (PodDto podDto : podDtos) {
      for (SeatDto seatDto : podDto.seats()) {
        if (playersWithTableLock.containsKey(seatDto.playerId())) {
          int tableLockNumber = playersWithTableLock.get(seatDto.playerId()).getTableLock();
          int seededTableNumber = podDtos.indexOf(podDto) + 1;

          if (seededTableNumber != tableLockNumber) {
            // load affected pods from database
            Pod seededPod = podService.getPodById(podDto.podId());
            Pod podAtLockedTable = podService.getPodByRoundAndTableNumber(tournament.getId(),
                seededPod.getRound(), tableLockNumber);
            // change pod / table numbers and save
            seededPod.setName(tableLockNumber);
            podService.save(seededPod);
            podAtLockedTable.setName(seededTableNumber);
            podService.save(podAtLockedTable);
            // swap table in return object - index is table number - 1
            Collections.swap(podDtos, tableLockNumber - 1, podDtos.indexOf(podDto));
          }
        }
      }
    }

    // rebuild list and correct podNames
    return IntStream.range(0, podDtos.size())
        .mapToObj(index -> {
          PodDto currentPod = podDtos.get(index);
          int expectedPodName = index + 1; // PodName should be index + 1

          if (currentPod.podName() != expectedPodName) {
            return currentPod.toBuilder()
                .podName(expectedPodName)
                .build();
          } else {
            // If podName is already correct, return the original PodDto
            return currentPod;
          }
        })
        .toList();
  }

  private void createAndAssignByePod(List<Player> players, Tournament tournament,
      int previousRound) {
    int numberOfByes = players.size() % POD_SIZE;


    if (numberOfByes > 0) {
      List<Player> playersWithBye = getPlayersForBye(tournament.getId(), players, previousRound,
          numberOfByes);

      Pod byePod = new Pod();
      byePod.setTournament(tournament);
      byePod.setType(PodType.SWISS);
      byePod.setRound(previousRound + 1);
      byePod.setName(999);

      for (Player player : playersWithBye) {
        Seat byeSeat = new Seat();
        byeSeat.setPod(byePod);
        byeSeat.setPlayer(player);
        byeSeat.setResult(Result.BYE);
        byeSeat.setSeat(byePod.getSeats().size() + 1);
        byePod.getSeats().add(byeSeat);
      }

      podService.save(byePod);
    }
  }

  private List<Player> getPlayersForBye(UUID tournamentId, List<Player> players, int previousRound, int numberOfByes) {
    List<Player> playersWithBye = new ArrayList<>();

    if (previousRound == 0) {
      for (int i = 0; i < numberOfByes; i++) {
        playersWithBye.add(players.getLast());
        players.removeLast();
      }
    } else {
      List<TournamentPlayer> tournamentPlayers = scoreService
          .calculatePlayerScoresAfterSwissRounds(tournamentId, previousRound);

      // remove players that already received a bye
      List<Pod> allByePodsForTournament = podService.getPodsByTournamentId(tournamentId);
      for (TournamentPlayer tournamentPlayer : tournamentPlayers) {
        for (Pod pod : allByePodsForTournament) {
          for (Seat seat : pod.getSeats()) {
            if (seat.getPlayer().getId().equals(tournamentPlayer.getId())) {
              tournamentPlayers.remove(tournamentPlayer);
            }
          }
        }
      }

      List<TournamentPlayer> tournamentPlayersSortedByScore = new ArrayList<>(
          tournamentPlayers.stream()
              .sorted(Comparator.comparing(TournamentPlayer::getScore).reversed())
              .toList());

      // remove players from possible pod list and add them to the BYE pod
      for (int i = 0; i < numberOfByes; i++) {
        /*
            TODO: potential improvement
              currently we get the players for a tournament and sort by score. If there are
              more players with the same lowest score then BYEs, the assignement of byes is
              determined by the order of the DB query and will not be random.
         */
        playersWithBye.add(tournamentPlayersSortedByScore.getLast().getPlayer());
        tournamentPlayersSortedByScore.removeLast();
      }
    }

    return playersWithBye;
  }

  private boolean backtrack(
      List<List<Integer>> groups,
      boolean[] used,
      int groupIdx,
      int groupsPerRound,
      int groupSize,
      int numPlayers) {
    if (groupIdx == groupsPerRound) {
      schedule.add(new ArrayList<>(groups));
      return true;
    }

    List<Integer> candidates = new ArrayList<>();
    for (int i = 0; i < numPlayers; i++) {
      if (!used[i]) {
        candidates.add(i);
      }
    }

    List<List<Integer>> combinations = generateCombinations(candidates, groupSize);
    for (List<Integer> combo : combinations) {
      if (isValidGroup(combo)) {
        for (int p : combo) {
          used[p] = true;
        }
        addPairs(combo);
        groups.add(combo);

        if (backtrack(groups, used, groupIdx + 1, groupsPerRound, groupSize, numPlayers)) {
          return true;
        }

        groups.remove(groups.size() - 1);
        removePairs(combo);
        for (int p : combo) {
          used[p] = false;
        }
      }
    }

    return false;
  }

  private void populateSeenPairs(UUID tournamentId) {
    // reset seen pairs due to shuffling and new ids for each player
    seenPairs = new HashSet<>();

    List<Pod> pods = podService.getPodsByTournamentId(tournamentId);
    for (Pod pod : pods) {
      List<Seat> seats = seatService.getSeatsByPodId(pod.getId());
      for (int i = 0; i < seats.size(); i++) {
        for (int j = i + 1; j < seats.size(); j++) {
          UUID id1 = seats.get(i).getPlayer().getId();
          UUID id2 = seats.get(j).getPlayer().getId();
          Integer idx1 = playerIdToIndex.get(id1);
          Integer idx2 = playerIdToIndex.get(id2);
          if (idx1 != null && idx2 != null) {
            seenPairs.add(makeKey(idx1, idx2));
          }
        }
      }
    }
  }

  private boolean isValidGroup(List<Integer> group) {
    for (int i = 0; i < group.size(); i++) {
      for (int j = i + 1; j < group.size(); j++) {
        String key = makeKey(group.get(i), group.get(j));
        if (seenPairs.contains(key)) {
          return false;
        }
      }
    }
    return true;
  }

  private void addPairs(List<Integer> group) {
    for (int i = 0; i < group.size(); i++) {
      for (int j = i + 1; j < group.size(); j++) {
        seenPairs.add(makeKey(group.get(i), group.get(j)));
      }
    }
  }

  private void removePairs(List<Integer> group) {
    for (int i = 0; i < group.size(); i++) {
      for (int j = i + 1; j < group.size(); j++) {
        seenPairs.remove(makeKey(group.get(i), group.get(j)));
      }
    }
  }

  private String makeKey(int a, int b) {
    return Math.min(a, b) + ":" + Math.max(a, b);
  }

  private List<List<Integer>> generateCombinations(List<Integer> players, int k) {
    List<List<Integer>> result = new ArrayList<>();
    generateCombinationsRecursive(players, k, 0, new ArrayList<>(), result);
    return result;
  }

  private void generateCombinationsRecursive(
      List<Integer> players, int k, int start, List<Integer> current, List<List<Integer>> result) {
    if (current.size() == k) {
      result.add(new ArrayList<>(current));
      return;
    }
    for (int i = start; i < players.size(); i++) {
      current.add(players.get(i));
      generateCombinationsRecursive(players, k, i + 1, current, result);
      current.remove(current.size() - 1);
    }
  }

  private List<RoundDto.PodDto> savePodsAndSeats(
      List<List<Integer>> groups, int round, Tournament tournament) {
    List<RoundDto.PodDto> podDtos = new ArrayList<>();
    int podName = 1;

    for (List<Integer> group : groups) {
      // Generate pod using the generatePod method
      Pod pod = generateAndPersistPod(round, podName++, tournament, PodType.SWISS);

      List<RoundDto.SeatDto> seats = new ArrayList<>();
      int seatNum = 1;

      // seat weighting
      Set<Player> playersInProd = new HashSet<>();
      for (int idx : group) {
        playersInProd.add(indexToPlayer.get(idx));
      }

      List<Player> playerSeatList = generateSeatOrder(tournament, playersInProd);
      for (Player player : playerSeatList) {
        seatService.generateAndPersistSeat(pod, player, seatNum);

        seats.add(
            RoundDto.SeatDto.builder()
                .playerId(player.getId())
                .nickname(player.getNickname())
                .seat(seatNum++)
                .firstname(player.getFirstname())
                .lastname(player.getLastname())
                .build());
      }

      podDtos.add(
          RoundDto.PodDto.builder().podId(pod.getId()).podName(pod.getName()).seats(seats).build());
    }

    return podDtos;
  }

  protected List<Player> generateSeatOrder(Tournament tournament, Set<Player> playersInProd) {
    Map<Player, Integer> seatHistory = new HashMap<>();

    // add all previous seats for all players in the pod
    for (Player player : playersInProd) {
      List<Seat> previousPlayerSeats = seatService.getPlayerSeatsByTournament(
          tournament.getId(), player.getId());

      Integer playerSeatHistory = 0;
      for (Seat seat : previousPlayerSeats) {
        playerSeatHistory += seat.getSeat();
      }
      seatHistory.put(player, playerSeatHistory);
    }

    // order so the player with the highest combined value is first in the list
    // -> will get the lowest seat next round
    return seatHistory.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  @Transactional
  public ResponseEntity<String> determineCut(UUID tournamentId, int cutTo) {

    Optional<Integer> optionalRoundNumber = podService.getLastPlayedRoundNumberByTournamentId(tournamentId);

    if (optionalRoundNumber.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    int roundNumber = optionalRoundNumber.get();
    List <TournamentPlayer> players = scoreService.calculatePlayerScoresAfterSwissRounds(tournamentId, roundNumber);

    if (cutTo == TOP_TEN_CUT) {
      return generateTopTenCut(players, roundNumber);
    } else {
      ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(ResponseMessages.RESPONSE_BAD_REQUEST_TOP_CUT);
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  private ResponseEntity<String> generateTopTenCut(List<TournamentPlayer> players,
    int lastRoundNumber) {
    List<TournamentPlayer> topTenPlayers = generateTopCut(players, TOP_TEN_CUT);
    int firstPod = 1;
    List<TournamentPlayer> topEightPlayers = placeFinalPlayersInPod(topTenPlayers, lastRoundNumber, firstPod);
    generateSemifinals(topEightPlayers, lastRoundNumber, firstPod);
    return ResponseEntity.status(200).build();
  }

  private List<TournamentPlayer> generateTopCut(List<TournamentPlayer> players, int cutSize) {
    return players.stream()
        .sorted(Comparator.comparing(TournamentPlayer::getScore).reversed())
        .limit(cutSize)
        .toList();
  }

  private void generateSemifinals(List<TournamentPlayer> players, int lastRound, int firstPodName) {

    int semifinalRoundNumber = lastRound + 1;

    /*
    Player seating table for semifinals
    Seat => player number in list
    Pod 1     | Pod 2
    1   =>  1 | 2   =>  2
    4   =>  4 | 3   =>  3
    5   =>  5 | 6   =>  6
    8   =>  8 | 7   =>  7
    */

    List<TournamentPlayer> semifinalsPod1 = new ArrayList<>();
    List<TournamentPlayer> semifinalsPod2 = new ArrayList<>();

    // Define pod assignment: 1 for Pod1, 2 for Pod2
    int[] podAssignments = {1, 2, 2, 1, 1, 2, 2, 1};

    for (int i = 0; i < players.size(); i++) {
      TournamentPlayer player = players.get(i);

      if (i >= podAssignments.length) {
        System.out.println("Unhandled case at index " + i);
        continue;
      }

      if (podAssignments[i] == 1) {
        semifinalsPod1.add(player);
      } else if (podAssignments[i] == 2) {
        semifinalsPod2.add(player);
      }
    }

    Pod podOne =
        generateAndPersistPod(
            semifinalRoundNumber,
            firstPodName,
            semifinalsPod1.getFirst().getTournament(),
            PodType.SEMIFINAL);
    findPlayerForSeating(semifinalsPod1, podOne);

    Pod podTwo =
        generateAndPersistPod(
            semifinalRoundNumber,
            firstPodName + 1,
            semifinalsPod2.getFirst().getTournament(),
            PodType.SEMIFINAL);
    findPlayerForSeating(semifinalsPod2, podTwo);
  }

  private List<TournamentPlayer> placeFinalPlayersInPod(
      List<TournamentPlayer> immutablePlayerList, int lastRoundNumber, int podNumber) {
    // first and second player for final
    List<TournamentPlayer> mutablePlayerList = new ArrayList<>(immutablePlayerList);

    List<TournamentPlayer> finalists = new ArrayList<>();

    TournamentPlayer finalistOne = mutablePlayerList.get(0);
    TournamentPlayer finalistTwo = mutablePlayerList.get(1);

    finalists.add(finalistOne);
    finalists.add(finalistTwo);

    // remove these player from top 10
    mutablePlayerList.remove(finalistOne);
    mutablePlayerList.remove(finalistTwo);

    int finals = 2;
    int finalRoundNumber = lastRoundNumber + finals;

    Pod pod =
        generateAndPersistPod(
            finalRoundNumber, podNumber, finalistOne.getTournament(), PodType.FINAL);

    findPlayerForSeating(finalists, pod);

    return mutablePlayerList;
  }

  private void findPlayerForSeating(List<TournamentPlayer> finalists, Pod pod) {
    for (int i = 0; i < finalists.size(); i++) {
      Player player = playerService.findPlayerById(finalists.get(i).getPlayer().getId());
      seatService.generateAndPersistSeat(pod, player, i + 1);
    }
  }

  private Pod generateAndPersistPod(
      int round, int podName, Tournament tournament, PodType podType) {
    Pod pod = new Pod();
    pod.setRound(round);
    pod.setName(podName);
    pod.setTournament(tournament);
    pod.setType(podType);
    podService.save(pod);
    return pod;
  }

  public List<RoundDto> getRoundsByTournamentId(UUID tournamentId) {
    // Fetch pods sorted by round and name
    List<Pod> pods = podService.getByTournamentIdOrderByRoundAscNameAsc(tournamentId);

    // Group pods by round number
    Map<Integer, List<Pod>> podsByRound = pods.stream()
        .collect(Collectors.groupingBy(Pod::getRound));

    // Build RoundDto for each round
    List<RoundDto> rounds = podsByRound.entrySet().stream()
        .map(entry -> {
          int roundNumber = entry.getKey();
          List<RoundDto.PodDto> podDtos = entry.getValue().stream()
              .map(pod -> {
                // For each pod, get seats (players + seat numbers)
                List<RoundDto.SeatDto> seats = pod.getSeats().stream()
                    .map(seat -> new RoundDto.SeatDto(
                        seat.getPlayer().getId(),
                        seat.getPlayer().getFirstname(),
                        seat.getPlayer().getLastname(),
                        seat.getPlayer().getNickname(),
                        seat.getSeat(), seat.getResult()))
                    .collect(Collectors.toList());

                return new RoundDto.PodDto(pod.getId(), pod.getName(), seats);
              })
              .collect(Collectors.toList());

          return new RoundDto(roundNumber, podDtos);
        })
        .sorted(Comparator.comparingInt(RoundDto::round))
        .collect(Collectors.toList());

    return rounds;
  }

  public Tournament getTournament(UUID tournamentId) {
    return tournamentRepository.findById(tournamentId).orElse(null);
  }

  @Transactional
  public ResponseEntity<Void> addPlayersToTournament(List<PlayerDto> players, UUID tournamentId) {
    List <Player> playerList = playerMapper.toDaoList(players);
    playerService.savePlayers(playerList);

    Tournament tournament = getTournament(tournamentId);
    List<TournamentPlayer> tournamentPlayers = new ArrayList<>();

    for (Player player : playerList) {
      //Create ID
      TournamentPlayerId tournamentPlayerId = new TournamentPlayerId();
      tournamentPlayerId.setPlayer(player.getId());
      tournamentPlayerId.setTournament(tournamentId);

      //Create Player
      TournamentPlayer tournamentPlayer = new TournamentPlayer();
      tournamentPlayer.setPlayer(player);
      tournamentPlayer.setTournament(tournament);
      tournamentPlayer.setId(tournamentPlayerId);

      tournamentPlayers.add(tournamentPlayer);

    }

    tournamentPlayerService.saveAll(tournamentPlayers);
    return ResponseEntity.ok().build();
  }

  public RoundDto getLatestRoundByTournamentId(UUID tournamentId) {
    // Fetch pods sorted by round and name
    List<Pod> pods = podService.findByTournamentIdOrderByRoundAscNameAsc(tournamentId);

    if (pods.isEmpty()) {
      return null; // or throw exception, depending on your API strategy
    }

    // Group pods by round number
    Map<Integer, List<Pod>> podsByRound = pods.stream()
        .collect(Collectors.groupingBy(Pod::getRound));

    // Get the latest round number
    int latestRound = podsByRound.keySet().stream()
        .max(Integer::compareTo)
        .orElseThrow(); // safe, since we already checked that pods is not empty

    // Convert latest round's pods to PodDto
    List<RoundDto.PodDto> podDtos = podsByRound.get(latestRound).stream()
        .map(pod -> {
          List<RoundDto.SeatDto> seats = pod.getSeats().stream()
              .map(seat -> new RoundDto.SeatDto(
                  seat.getPlayer().getId(),
                  seat.getPlayer().getFirstname(),
                  seat.getPlayer().getLastname(),
                  seat.getPlayer().getNickname(),
                  seat.getSeat(), seat.getResult()))
              .collect(Collectors.toList());

          return new RoundDto.PodDto(pod.getId(), pod.getName(), seats);
        })
        .collect(Collectors.toList());

    return new RoundDto(latestRound, podDtos);
  }

  public Tournament getTournamentByTournamentId(UUID tournamentId) {
    return tournamentRepository.findById(tournamentId).orElse(null);
  }

  public Integer findMaxRound(UUID tournamentId) {
    return tournamentRepository.findMaxRound(tournamentId);
  }
}
