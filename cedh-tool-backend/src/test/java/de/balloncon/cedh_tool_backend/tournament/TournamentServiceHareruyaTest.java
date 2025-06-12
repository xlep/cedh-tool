package de.balloncon.cedh_tool_backend.tournament;

import static org.assertj.core.api.Assertions.assertThat;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.dto.RoundDto;
import de.balloncon.cedh_tool_backend.dto.RoundDto.PodDto;
import de.balloncon.cedh_tool_backend.dto.RoundDto.SeatDto;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.score.ScoreService;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.seat.SeatService;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerService;
import jakarta.transaction.Transactional;
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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.transaction.TestTransaction;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class TournamentServiceHareruyaTest {

  @Autowired private TournamentServiceHareruya tournamentServiceHareruya;
  @Autowired private PodService podService;
  @Autowired private SeatService seatService;
  @Autowired private TournamentPlayerService tournamentPlayerService;
  @Autowired private ScoreService scoreService;
  @Autowired private TournamentService tournamentService;

  private static String sortPair(String id1, String id2) {
    List<String> pairings = Arrays.asList(id1, id2);
    Collections.sort(pairings);

    return String.join(":", pairings);
  }

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
      tournamentServiceHareruya.generateNextRound(tournamentId);
    }

    // Fetch the updated pods and seats
    List<Pod> pods = podService.getPodsAndSeatsByTournamentId(tournamentId);

    assertThat(pods).isNotEmpty().hasSize(75);

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
          assertThat(addPairingsResult)
              .isTrue()
              .as(String.format("Double Pairing detected. Found pairing duplicate %s", pairing));
        }
      }
    }
  }

  @Test
  void testSeatWeightingWithMultipleRounds() {
    // Use the provided tournament ID
    UUID tournamentId = UUID.fromString("e29fbe3f-1755-43cc-a27a-393ec6d80a09");

    // Track all pairings for uniqueness
    // Generate 5 rounds
    for (int round = 0; round < 5; round++) {
      // Generate the next round
      tournamentServiceHareruya.generateNextRound(tournamentId);
    }

    Map<TournamentPlayer, Integer> playerSeatSum = new HashMap<>();
    List<TournamentPlayer> players = tournamentPlayerService.findByTournament(tournamentId);
    for (TournamentPlayer tournamentPlayer : players) {
      List<Seat> playerSeatsByTournament =
          seatService.getPlayerSeatsByTournament(
              tournamentId, tournamentPlayer.getPlayer().getId());

      Integer seatSum = 0;
      for (Seat seat : playerSeatsByTournament) {
        seatSum += seat.getSeat();
      }
      playerSeatSum.put(tournamentPlayer, seatSum);
    }

    assertThat(playerSeatSum).hasSize(60);
    assertThat(playerSeatSum.values()).allMatch(sum -> sum >= 11 && sum <= 15);
  }

  @Test
  void testTableLockInMultipleRounds() {
    // Use the provided tournament ID
    UUID tournamentId = UUID.fromString("e29fbe3f-1755-43cc-a27a-393ec6d80a09");

    UUID playerLockOneId = UUID.fromString("0a19432f-509f-413a-9e4c-b25089b9b60c"); // nick17
    UUID playerLockTwoId = UUID.fromString("7824f5a5-88da-404d-9f18-fc31d7fbc16c"); // nick35

    // lock players to tables
    int tableLockOne = 5;
    TournamentPlayer playerLockOne =
        tournamentPlayerService.findPlayerByTournamentAndID(tournamentId, playerLockOneId);
    playerLockOne.setTableLock(tableLockOne);
    tournamentPlayerService.save(playerLockOne);

    int tableLockTwo = 12;
    TournamentPlayer playerLockTwo =
        tournamentPlayerService.findPlayerByTournamentAndID(tournamentId, playerLockTwoId);
    playerLockTwo.setTableLock(tableLockTwo);
    tournamentPlayerService.save(playerLockTwo);

    // Track all pairings for uniqueness
    Set<String> pairings = new HashSet<>();
    List<String> prairingsList = new ArrayList<>();
    Boolean addPairingsResult;

    // Generate 5 rounds
    for (int round = 1; round <= 5; round++) {
      // Generate the next round
      RoundDto roundDto = tournamentServiceHareruya.generateNextRound(tournamentId);

      // needed to ensure a commit the transaction before we start
      TestTransaction.flagForCommit();
      TestTransaction.end();
      TestTransaction.start();

      // Validate swapped pods in database
      Pod podTableLockOne =
          podService.findPodByRoundAndPodNumber(tournamentId, round, tableLockOne);
      assertThat(podTableLockOne.getSeats())
          .as(String.format("Checking player %s in round %s in DB", playerLockOneId, round))
          .extracting(Seat::getPlayer)
          .extracting(Player::getId)
          .contains(playerLockOneId);

      Pod podTableLockTwo =
          podService.findPodByRoundAndPodNumber(tournamentId, round, tableLockTwo);
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

    tournamentServiceHareruya.generateNextRound(tournamentId);

    // needed to ensure a commit the transaction before we start
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();

    List<Pod> pods = podService.getPodsAndSeatsByTournamentId(tournamentId);
    List<PlayerDto> tournamentPlayers =
        tournamentPlayerService.getPlayerDtosByTournament(tournamentId);

    // We have 26 players, so we should have 7 pods (with 1 BYE pod containing 2 players
    assertThat(pods).isNotEmpty().hasSize(7);

    // make sure every player is accounted for
    List<Player> seatedPlayers = new ArrayList<>();
    for (Pod pod : pods) {
      for (Seat seat : pod.getSeats()) {
        seatedPlayers.add(seat.getPlayer());
      }
    }
    assertThat(seatedPlayers.size()).isEqualTo(tournamentPlayers.size());
  }

  @Test
  void testByeSeatGenerationByScore() {
    UUID tournamentId = UUID.fromString("28a471f7-2adb-45ed-b9db-1376b473786d");

    assertThat(tournamentService.findMaxRound(tournamentId)).isEqualTo(2);

    tournamentServiceHareruya.generateNextRound(tournamentId);

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

    assertThat(byePod).isNotNull();
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
}
