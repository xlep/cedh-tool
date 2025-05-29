package de.balloncon.cedh_tool_backend.tournament;

import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.dto.RoundDto;
import de.balloncon.cedh_tool_backend.dto.RoundDto.PodDto;
import de.balloncon.cedh_tool_backend.dto.RoundDto.SeatDto;
import de.balloncon.cedh_tool_backend.mapper.PodMapper;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodRepository;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.pod.PodType;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.seat.SeatRepository;
import de.balloncon.cedh_tool_backend.seat.SeatService;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerRepository;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerService;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerStatus;
import de.balloncon.cedh_tool_backend.util.ResponseMessages;
import de.balloncon.cedh_tool_backend.util.ShuffleUtil;
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
  private TournamentPlayerRepository tournamentPlayerRepository;
  @Autowired
  private PodRepository podRepository;
  @Autowired
  private SeatRepository seatRepository;
  @Autowired
  private TournamentRepository tournamentRepository;
  @Autowired
  private PodMapper podMapper;
  @Autowired
  private TournamentPlayerService tournamentPlayerService;
  @Autowired
  private PodService podService;
  @Autowired
  private PlayerService playerService;
  @Autowired
  private SeatService seatService;
  @Autowired
  private ShuffleUtil shuffleUtil;

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
    List<Player> players = tournamentPlayerRepository
        .findByTournament(tournamentId, TournamentPlayerStatus.active)
        .stream()
        .map(TournamentPlayer::getPlayer)
        .collect(Collectors.toList());
    int previousRound = tournamentRepository.findMaxRound(tournamentId) == null
        ? 0
        : tournamentRepository.findMaxRound(tournamentId);

    // Shuffle the player list each round, since the seating algorithm uses the first possible pod
    shuffleUtil.shuffleList(players);

    // if a BYE is needed, give it to the lowest ranking players
    // !!! REMOVES players that are assigned a bye from the player list
    createAndAssignByePod(players, tournament, previousRound);

    int numPlayers = players.size();
    int groupsPerRound = numPlayers / POD_SIZE;

    // reset UUID:INT due to shuffling and new player IDs (just to be safe from orphant entry sets because of drops)
    indexToPlayer = new HashMap<>();
    playerIdToIndex = new HashMap<>();
    // Map UUIDs to integers
    for (int i = 0; i < players.size(); i++) {
      indexToPlayer.put(i, players.get(i));
      playerIdToIndex.put(players.get(i).getId(), i);
    }

    // Populate seenPairs from existing pods
    populateSeenPairs(tournamentId);

    List<List<Integer>> groups = new ArrayList<>();
    boolean[] used = new boolean[numPlayers];

    boolean solved = backtrack(groups, used, 0, groupsPerRound, POD_SIZE, numPlayers);
    if (!solved) {
      throw new RuntimeException("Could not generate valid round.");
    }

    // Save result to DB
    int nextRound = previousRound + 1;
    List<RoundDto.PodDto> podDtos = savePodsAndSeats(groups, nextRound, tournament);
    // swap pod-/table-names according to table-locks
    List<PodDto> podDtosTableLockAdjusted = adjustForTableLocks(tournament, podDtos);

    return RoundDto.builder().round(nextRound).pods(podDtosTableLockAdjusted).build();
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
        byeSeat.setResult(Result.bye);
        byeSeat.setSeat(byePod.getSeats().size() + 1);
        byePod.getSeats().add(byeSeat);
      }

      podRepository.save(byePod);
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
      List<TournamentPlayer> tournamentPlayers = tournamentPlayerService
          .calculatePlayerScoresAfterSwissRounds(tournamentId, previousRound);

      // remove players that already received a bye
      List<Pod> allByePodsForTournament = podRepository.findAllByePodsForTournament(tournamentId);
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

    List<Pod> pods = podRepository.findByTournamentId(tournamentId);
    for (Pod pod : pods) {
      List<Seat> seats = seatRepository.findByPodId(pod.getId());
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
    List <TournamentPlayer> players = tournamentPlayerService.calculatePlayerScoresAfterSwissRounds(tournamentId, roundNumber);

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

    int middle = players.size() / 2;
    int semifinalRoundNumber = lastRound + 1;

    shuffleUtil.shuffleList(players);

    List<TournamentPlayer> listOfFirstSemifinalPlayers;
    listOfFirstSemifinalPlayers = players.subList(0, middle);

    List<TournamentPlayer> listOfSecondSemifinalPlayers;
    listOfSecondSemifinalPlayers = players.subList(middle, players.size());

    Pod podOne =
        generateAndPersistPod(
            semifinalRoundNumber,
            firstPodName,
            listOfFirstSemifinalPlayers.getFirst().getTournament(),
            PodType.SEMIFINAL);
    findPlayerForSeating(listOfFirstSemifinalPlayers, podOne);

    Pod podTwo =
        generateAndPersistPod(
            semifinalRoundNumber,
            firstPodName + 1,
            listOfSecondSemifinalPlayers.getFirst().getTournament(),
            PodType.SEMIFINAL);
    findPlayerForSeating(listOfSecondSemifinalPlayers, podTwo);
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
    return podRepository.save(pod); // This will persist the pod and return the saved pod
  }

  public List<RoundDto> getRoundsByTournamentId(UUID tournamentId) {
    // Fetch pods sorted by round and name
    List<Pod> pods = podRepository.findByTournamentIdOrderByRoundAscNameAsc(tournamentId);

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
                        seat.getSeat()))
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
}
