package de.balloncon.cedh_tool_backend.tournament;

import static org.assertj.core.api.Assertions.assertThat;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerService;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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
class TournamentServiceTest {

  @Autowired TournamentService tournamentService;

  @Autowired PodService podService;

  @Autowired TournamentPlayerService tournamentPlayerService;

  @Autowired PlayerService playerService;

  @Autowired private TournamentRepository tournamentRepository;

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
  void testByePodGeneration() {
    UUID tournamentId = UUID.fromString("5a6b7c8d-9e0f-1111-2222-333344455566");

    tournamentService.generateNextRound(tournamentId);

    // needed to ensure a commit the transaction before we start
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();

    List<Pod> pods = podService.getPodsAndSeatsByTournamentId(tournamentId);
    List<PlayerDto> tournamentPlayers = tournamentPlayerService.getPlayersById(tournamentId);

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

    assertThat(tournamentRepository.findMaxRound(tournamentId)).isEqualTo(2);

    tournamentService.generateNextRound(tournamentId);

    List<Pod> roundThreePods = podService.getPodsByRoundNumber(tournamentId, 3);
    List<TournamentPlayer> tournamentPlayers =
        tournamentPlayerService.calculatePlayerScoresAfterSwissRounds(tournamentId, 2);
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
  }
}
