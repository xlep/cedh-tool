package de.balloncon.cedh_tool_backend.tournament;

import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.dto.RoundDto;
import de.balloncon.cedh_tool_backend.dto.RoundDto.PodDto;
import de.balloncon.cedh_tool_backend.dto.RoundDto.SeatDto;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.pod.PodType;
import de.balloncon.cedh_tool_backend.score.ScoreService;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.seat.SeatService;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerService;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerStatus;
import de.balloncon.cedh_tool_backend.util.ShuffleUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentServiceHareruya implements TournamentLogic {
  private static final int POD_SIZE = 4;
  private final List<List<List<Integer>>> schedule = new ArrayList<>();
  @Autowired private ShuffleUtil shuffleUtil;
  @Autowired private TournamentService tournamentService;
  @Autowired private SeatService seatService;
  @Autowired private TournamentPlayerService tournamentPlayerService;
  @Autowired private PodService podService;
  @Autowired private ScoreService scoreService;
  private Map<Integer, Player> indexToPlayer = new HashMap<>();
  private Map<UUID, Integer> playerIdToIndex = new HashMap<>();
  private Set<String> seenPairs = new HashSet<>();

  public RoundDto generateNextRound(UUID tournamentId) {
    //    List<Player> players =seatService.getPlayerByTournament(tournamentId);

    List<TournamentPlayer> tournamentPlayers =
        tournamentPlayerService.getActiveTournamentPlayers(tournamentId);
    Tournament tournament = tournamentService.getTournament(tournamentId);

    // Filter active players from tournamentPlayers
    List<Player> players =
        tournamentPlayers.stream()
            .filter(tp -> tp.getStatus() == TournamentPlayerStatus.active)
            .map(TournamentPlayer::getPlayer)
            .collect(Collectors.toList());

    Integer maxRound = tournamentService.findMaxRound(tournamentId);
    int previousRound = maxRound == null ? 0 : maxRound;

    // Shuffle the player list each round, since the seating algorithm uses the first possible pod
    shuffleUtil.shuffleList(players);

    // if a BYE is needed, give it to the lowest ranking players
    // !!! REMOVES players that are assigned a bye from the player list
    createAndAssignByePod(players, tournament, previousRound);

    int numPlayers = players.size();
    int groupsPerRound = numPlayers / POD_SIZE;

    // reset UUID:INT due to shuffling and new player IDs (just to be safe from orphant entry sets
    // because of drops)
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

  private void createAndAssignByePod(
      List<Player> players, Tournament tournament, int previousRound) {
    int numberOfByes = players.size() % POD_SIZE;

    if (numberOfByes > 0) {
      List<Player> playersWithBye =
          getPlayersForBye(tournament.getId(), players, previousRound, numberOfByes);

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

  private List<PodDto> adjustForTableLocks(Tournament tournament, List<PodDto> podDtos) {
    // TODO: using the DTO classes feels clunky in general. Why are using these for the internal
    // logic around round generation?
    // TODO: additional note; if we can refactor the pod generation so the pods only get persisted
    // when everyhing is prepared, we can make this proces much cleaner
    Map<UUID, TournamentPlayer> playersWithTableLock =
        tournamentPlayerService.getPlayersForTournament(tournament.getId()).stream()
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
            Pod podAtLockedTable =
                podService.getPodByRoundAndTableNumber(
                    tournament.getId(), seededPod.getRound(), tableLockNumber);
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
        .mapToObj(
            index -> {
              PodDto currentPod = podDtos.get(index);
              int expectedPodName = index + 1; // PodName should be index + 1

              if (currentPod.podName() != expectedPodName) {
                return currentPod.toBuilder().podName(expectedPodName).build();
              } else {
                // If podName is already correct, return the original PodDto
                return currentPod;
              }
            })
        .toList();
  }

  private List<Player> getPlayersForBye(
      UUID tournamentId, List<Player> players, int previousRound, int numberOfByes) {
    List<Player> playersWithBye = new ArrayList<>();

    if (previousRound == 0) {
      for (int i = 0; i < numberOfByes; i++) {
        playersWithBye.add(players.getLast());
        players.removeLast();
      }
    } else {
      List<TournamentPlayer> tournamentPlayers =
          scoreService.calculatePlayerScoresAfterSwissRounds(tournamentId, previousRound);

      // remove players that already received a bye
      List<Pod> allByePodsForTournament = podService.getAllByePodsByTournamentId(tournamentId);
      for (TournamentPlayer tournamentPlayer : tournamentPlayers) {
        for (Pod pod : allByePodsForTournament) {
          for (Seat seat : pod.getSeats()) {
            if (seat.getPlayer().getId().equals(tournamentPlayer.getId())) {
              tournamentPlayers.remove(tournamentPlayer);
            }
          }
        }
      }

      List<TournamentPlayer> tournamentPlayersSortedByScore =
          new ArrayList<>(
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

  protected List<Player> generateSeatOrder(Tournament tournament, Set<Player> playersInProd) {
    Map<Player, Integer> seatHistory = new HashMap<>();

    // add all previous seats for all players in the pod
    for (Player player : playersInProd) {
      List<Seat> previousPlayerSeats =
          seatService.getPlayerSeatsByTournament(tournament.getId(), player.getId());

      Integer playerSeatHistory = 0;
      for (Seat seat : previousPlayerSeats) {
        playerSeatHistory += seat.getSeat();
      }
      seatHistory.put(player, playerSeatHistory);
    }

    // order so the player with the highest combined value is first in the list
    // -> will get the lowest seat next round
    return seatHistory.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }
}
