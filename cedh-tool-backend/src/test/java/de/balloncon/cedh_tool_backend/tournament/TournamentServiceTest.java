package de.balloncon.cedh_tool_backend.tournament;

import static org.assertj.core.api.Assertions.assertThat;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.dto.RoundDto;
import de.balloncon.cedh_tool_backend.dto.RoundDto.PodDto;
import de.balloncon.cedh_tool_backend.dto.RoundDto.SeatDto;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodRepository;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.pod.PodType;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.seat.SeatService;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerId;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerRepository;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerService;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerStatus;
import de.balloncon.cedh_tool_backend.score.ScoreService;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.transaction.TestTransaction;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class TournamentServiceTest {

  @Autowired TournamentService tournamentService;

  @Autowired PodService podService;

  @Autowired TournamentPlayerService tournamentPlayerService;

  @Autowired PlayerService playerService;

  @Autowired SeatService seatService;

  @Autowired private TournamentRepository tournamentRepository;

  @Autowired private TournamentPlayerRepository tournamentPlayerRepository;
  @Autowired
  private PodRepository podRepository;

  @Autowired private ScoreService scoreService;

  // Test is for 60 player tournaments
  @Test
  void testNoRepeatedPairingsForFiveRounds() {
    // Use the provided tournament ID
    UUID tournamentId = UUID.fromString("e29fbe3f-1755-43cc-a27a-393ec6d80a09");

    // Track all pairings for uniqueness
    Set<String> pairings = new HashSet<>();
    List<String> prairingsList = new ArrayList<>();
    Boolean addPairingsResult;

    // Generate 5 rounds
    for (int round = 0; round < 5; round++) {
      // Generate the next round
      tournamentService.generateNextRound(tournamentId);
    }

    // Fetch the updated pods and seats
    List<Pod> pods = podService.getPodsAndSeatsByTournamentId(tournamentId);

    assertThat(pods)
        .isNotEmpty()
        .hasSize(75);

    // For each pod, generate pairings and ensure no repeats
    for (Pod pod : pods) {
      ArrayList<Seat> seats = new ArrayList<>(pod.getSeats());
      for (int i = 0; i < seats.size(); i++) {
        for (int j = i + 1; j < seats.size(); j++) {
          String pairing =
              sortPair(
                  seats.get(i).getPlayer().getId().toString(),
                  seats.get(j).getPlayer().getId().toString());
          // Ensure pairing is stored in consistent order
          addPairingsResult = pairings.add(pairing);
          prairingsList.add(pairing);
          assertThat(addPairingsResult).isTrue()
              .as(String.format("Double Pairing detected. Found pairing duplicate %s", pairing));
        }
      }
    }
  }

  @Disabled
  @Test
  void testTableLockInMultipleRounds() {
    // Use the provided tournament ID
    UUID tournamentId = UUID.fromString("e29fbe3f-1755-43cc-a27a-393ec6d80a09");

    UUID playerLockOneId = UUID.fromString("0a19432f-509f-413a-9e4c-b25089b9b60c"); // nick17
    UUID playerLockTwoId = UUID.fromString("7824f5a5-88da-404d-9f18-fc31d7fbc16c"); // nick35

    // lock players to tables
    int tableLockOne = 5;
    TournamentPlayer playerLockOne = tournamentPlayerRepository.findByTournamentAndPlayer(
        tournamentId, playerLockOneId);
    playerLockOne.setTableLock(tableLockOne);
    tournamentPlayerRepository.save(playerLockOne);

    int tableLockTwo = 12;
    TournamentPlayer playerLockTwo = tournamentPlayerRepository.findByTournamentAndPlayer(
        tournamentId, playerLockTwoId);
    playerLockTwo.setTableLock(tableLockTwo);
    tournamentPlayerRepository.save(playerLockTwo);

    // Track all pairings for uniqueness
    Set<String> pairings = new HashSet<>();
    List<String> prairingsList = new ArrayList<>();
    Boolean addPairingsResult;

    // Generate 5 rounds
    for (int round = 1; round <= 5; round++) {
      // Generate the next round
      RoundDto roundDto = tournamentService.generateNextRound(tournamentId);

      // needed to ensure a commit the transaction before we start
      TestTransaction.flagForCommit();
      TestTransaction.end();
      TestTransaction.start();

      // Validate swapped pods in database
      Pod podTableLockOne = podRepository.findPodByRoundAndPodNumber(tournamentId, round,
          tableLockOne);
      assertThat(podTableLockOne.getSeats())
          .as(String.format("Checking player %s in round %s in DB", playerLockOneId, round))
          .extracting(Seat::getPlayer)
          .extracting(Player::getId)
          .contains(playerLockOneId);

      Pod podTableLockTwo = podRepository.findPodByRoundAndPodNumber(tournamentId, round,
          tableLockTwo);
      assertThat(podTableLockTwo.getSeats())
          .as(String.format("Checking player %s in round %s in DB", playerLockTwoId, round))
          .extracting(Seat::getPlayer)
          .extracting(Player::getId)
          .contains(playerLockTwoId);

      // Validate table locks in return DTO
      assertThat(roundDto.pods().get(tableLockOne - 1))
          .extracting(PodDto::podName)
          .isEqualTo(tableLockOne);
      assertThat(roundDto.pods().get(tableLockOne - 1).seats())
          .as(String.format("Checking player %s in round %s in return DTO", playerLockOneId, round))
          .extracting(SeatDto::playerId)
          .contains(playerLockOneId);

      assertThat(roundDto.pods().get(tableLockOne - 1))
          .extracting(PodDto::podName)
          .isEqualTo(tableLockOne);
      assertThat(roundDto.pods().get(tableLockTwo - 1).seats())
          .as(String.format("Checking player %s in round %s in return DTO", playerLockTwoId, round))
          .extracting(SeatDto::playerId)
          .contains(playerLockTwoId);
    }
  }

  @Test
  void testByePodGeneration() {
    UUID tournamentId = UUID.fromString("5a6b7c8d-9e0f-1111-2222-333344455566");

    tournamentService.generateNextRound(tournamentId);

    // needed to ensure a commit the transaction before we start
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();

    List<Pod> pods = podService.getPodsAndSeatsByTournamentId(tournamentId);
    List<PlayerDto> tournamentPlayers = tournamentPlayerService.getPlayerDtosByTournament(tournamentId);

    // We have 26 players, so we should have 7 pods (with 1 BYE pod containing 2 players
    assertThat(pods)
        .isNotEmpty()
        .hasSize(7);

    // make sure every player is accounted for
    List<Player> seatedPlayers = new ArrayList<>();
    for (Pod pod : pods) {
      for (Seat seat : pod.getSeats()) {
        seatedPlayers.add(seat.getPlayer());
      }
    }
    assertThat(seatedPlayers.size())
        .isEqualTo(tournamentPlayers.size());

  }

  @Test
  void testByeSeatGenerationByScore() {
    UUID tournamentId = UUID.fromString("28a471f7-2adb-45ed-b9db-1376b473786d");

    assertThat(tournamentRepository.findMaxRound(tournamentId)).isEqualTo(2);

    tournamentService.generateNextRound(tournamentId);

    List<Pod> roundThreePods = podService.getPodsByRoundNumber(tournamentId, 3);
    List<TournamentPlayer> tournamentPlayers =
        scoreService.calculatePlayerScoresAfterSwissRounds(tournamentId, 2);
    List<TournamentPlayer> tournamentPlayersSortedByScore =
        new ArrayList<>(
            tournamentPlayers.stream()
                .sorted(Comparator.comparing(TournamentPlayer::getScore).reversed())
                .toList());

    Pod byePod = null;
    for (Pod pod : roundThreePods) {
      if (pod.getName() == 999) {
        byePod = pod;
      }
    }

    assertThat(byePod)
        .isNotNull();
    // this might break if the logic to create BYEs by player score changes
    assertThat(byePod.getSeats())
        .hasSize(2)
        .extracting(seat -> seat.getPlayer().getId())
        .contains(tournamentPlayersSortedByScore.getLast().getPlayer().getId())
        .contains(
            tournamentPlayersSortedByScore
                .get(tournamentPlayersSortedByScore.size() - 1)
                .getPlayer()
                .getId());
  }

  private static String sortPair(String id1, String id2) {
    List<String> pairings = Arrays.asList(id1, id2);
    Collections.sort(pairings);

    return String.join(":", pairings);
  }

  @Test
  void testTopTenCut() {

    int cutSize = 10;
    int semifinalRoundNumber = 6;
    int finalRoundNumber = 7;

    UUID tournamentId = UUID.fromString("7addec25-9af0-452f-9e01-6481892e545d");

    tournamentService.determineCut(tournamentId, cutSize);

    List<Pod> semifinalPods = podService.getPodsByRoundNumber(tournamentId, semifinalRoundNumber);
    List<Pod> finalPods = podService.getPodsByRoundNumber(tournamentId, finalRoundNumber);

    assert semifinalPods.size() == 2;
    assert finalPods.size() == 1;
    //todo this test needs to be improved to reflect the seating order
    /*
    Player seating table for semifinals
    Seat => player number in list
    Pod 1     | Pod 2
    1   =>  1 | 2   =>  2
    4   =>  4 | 3   =>  3
    5   =>  5 | 6   =>  6
    8   =>  8 | 7   =>  7
    */
  }

  @Test
  void testGenerateSeatOrder() {
    UUID tournamentId = UUID.fromString("28a471f7-2adb-45ed-b9db-1376b473786d");

    Set<Player> players = new HashSet<>();
    Player firebrand = playerService.findPlayerById(UUID.fromString("00000000-0000-0000-0000-000000000001")); // Firebrand -> 1+1 = 2
    Player nightshade = playerService.findPlayerById(UUID.fromString("00000000-0000-0000-0000-000000000002")); // Nightshade -> 2+2 = 4
    Player ironclad = playerService.findPlayerById(UUID.fromString("00000000-0000-0000-0000-000000000003")); // Ironclad -> 3+3 = 6
    Player ghostwalker = playerService.findPlayerById(UUID.fromString("00000000-0000-0000-0000-000000000004")); // Ghostwalker -> 4+4 = 8

    players.add(nightshade);
    players.add(ironclad);
    players.add(ghostwalker);
    players.add(firebrand);

    Tournament tournament = tournamentRepository.findTournamentById(tournamentId);
    List<Player> weightedSeats = tournamentService.generateSeatOrder(tournament, players);

    assertThat(weightedSeats)
        .hasSize(4)
        .containsExactly(
            ghostwalker,
            ironclad,
            nightshade,
            firebrand
        );
  }

  @Test
  void testSeatWeightingWithMultipleRounds() {
    // Use the provided tournament ID
    UUID tournamentId = UUID.fromString("e29fbe3f-1755-43cc-a27a-393ec6d80a09");
    Tournament tournamentById = tournamentRepository.findTournamentById(tournamentId);

    // Track all pairings for uniqueness
    // Generate 5 rounds
    for (int round = 0; round < 5; round++) {
      // Generate the next round
      tournamentService.generateNextRound(tournamentId);
    }

    Map<TournamentPlayer, Integer> playerSeatSum = new HashMap<>();
    List<TournamentPlayer> players = tournamentPlayerRepository.findByTournament(tournamentId);
    for (TournamentPlayer tournamentPlayer : players) {
      List<Seat> playerSeatsByTournament = seatService.getPlayerSeatsByTournament(tournamentId,
          tournamentPlayer.getPlayer().getId());

      Integer seatSum = 0;
      for (Seat seat : playerSeatsByTournament) {
        seatSum += seat.getSeat();
      }
      playerSeatSum.put(tournamentPlayer, seatSum);
    }

    assertThat(playerSeatSum)
        .hasSize(60);
    assertThat(playerSeatSum.values())
        .allMatch(sum -> sum >= 11 && sum <= 15);
  }

  private Tournament generateTestTournament() {
    Tournament tournament = new Tournament();
    tournament.setMode("Hareruya");
    tournament.setName("test top ten cut");
    tournamentService.save(tournament);
    return tournament;
  }

  private void generatePreviousRoundPod(Tournament tournament, int previousRoundNumber) {
    Pod pod = new Pod();
    pod.setType(PodType.SWISS);
    pod.setRound(previousRoundNumber);
    pod.setTournament(tournament);
    pod.setName(1);
    podService.save(pod);
  }

  private void generateTestTournamentPlayers(Tournament tournament, List<Player> playersList) {
    Long multiplier = 1L;
    Long baseScore = 100L;

    for (Player player : playersList) {
      TournamentPlayerId playerId = new TournamentPlayerId();
      playerId.setTournament(tournament.getId());
      playerId.setPlayer(player.getId());

      TournamentPlayer tournamentPlayer = new TournamentPlayer();
      tournamentPlayer.setId(playerId);
      tournamentPlayer.setTournament(tournament);
      tournamentPlayer.setPlayer(player);
      tournamentPlayer.setScore(BigDecimal.valueOf(baseScore * multiplier));
      tournamentPlayer.setStatus(TournamentPlayerStatus.active);

      multiplier++;
      tournamentPlayerService.save(tournamentPlayer);
    }
  }

  private List<Player> generateTestPlayers() {
    List<Player> players = new ArrayList<>();

    String[] firstnames = {
        "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"
    };

    for (int i = 0; i < 10; i++) {
      Player player = new Player();
      player.setFirstname(firstnames[i]);
      player.setLastname("last" + (i + 1));
      player.setNickname("nick_" + (i + 1));
      players.add(player);
    }

    playerService.savePlayers(players);
    return players;
  }
}
