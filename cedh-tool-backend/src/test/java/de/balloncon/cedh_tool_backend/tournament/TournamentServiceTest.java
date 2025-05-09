package de.balloncon.cedh_tool_backend.tournament;

import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.seat.SeatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class TournamentServiceTest {

    @Autowired
    TournamentService tournamentService;

    @Autowired
    PodService podService;

    @Autowired
    SeatService seatService;

    @Test
    void generateNextRound() {
        final UUID tournamentId = UUID.fromString("e29fbe3f-1755-43cc-a27a-393ec6d80a09");
        tournamentService.generateNextRound(tournamentId);
        Optional<List<Pod>> optionalPods = podService.getPodsByTournamentId(tournamentId);

        if (optionalPods.isPresent()) {
            List<Pod> pods = optionalPods.orElse(Collections.emptyList());
            assertThat(optionalPods).isNotNull();
            assertThat(pods.size()).isEqualTo(15);
        }
      }

      //These Test is specifically for 60 players and the Summer cEDH II event
@Test
void generateFourRoundsWithNoRepeat() {
    final UUID tournamentId = UUID.fromString("e29fbe3f-1755-43cc-a27a-393ec6d80a09");

    // Generate multiple rounds
    for (int i = 0; i < 4; i++) {
        tournamentService.generateNextRound(tournamentId);
    }

    List<Pod> pods = podService.getPodsByTournamentId(tournamentId).orElse(Collections.emptyList());

    // Gather all seats from all pods
    List<Seat> seats = new ArrayList<>();
    for (Pod pod : pods) {
        List<Seat> podSeats = seatService.getSeatsByPodId(pod.getId()).orElse(Collections.emptyList());
        seats.addAll(podSeats);
    }

    // Build the list of player pods
    List<List<Player>> playerPods = new ArrayList<>();
    List<Player> currentPod = new ArrayList<>();
    for (Seat seat : seats) {
        currentPod.add(seat.getPlayer());
        if (currentPod.size() == 4) {
            playerPods.add(new ArrayList<>(currentPod));
            currentPod.clear();
        }
    }

    // Initialize opponent tracking map
    Map<UUID, Set<UUID>> opponentMap = new HashMap<>();

    for (List<Player> pod : playerPods) {
        for (Player player : pod) {
            opponentMap.putIfAbsent(player.getId(), new HashSet<>());
        }

        for (Player player : pod) {
            UUID playerId = player.getId();
            Set<UUID> opponents = opponentMap.get(playerId);

            for (Player opponent : pod) {
                UUID opponentId = opponent.getId();
                if (!opponentId.equals(playerId)) {
                    assertThat(opponents)
                            .withFailMessage("Player %s already played against opponent %s", playerId, opponentId)
                            .doesNotContain(opponentId);
                    opponents.add(opponentId);
                }
            }
        }
    }
}
    //These Test is specifically for 60 players and the Summer cEDH II event
    @Test
    void generateFiveRoundsWithAtMostOneRepeat() {
        final UUID tournamentId = UUID.fromString("e29fbe3f-1755-43cc-a27a-393ec6d80a09");

        // Generate 5 rounds
        for (int i = 0; i < 5; i++) {
            tournamentService.generateNextRound(tournamentId);
        }

        List<Pod> pods = podService.getPodsByTournamentId(tournamentId).orElse(Collections.emptyList());

        // Gather all seats from all pods
        List<Seat> seats = new ArrayList<>();
        for (Pod pod : pods) {
            List<Seat> podSeats = seatService.getSeatsByPodId(pod.getId()).orElse(Collections.emptyList());
            seats.addAll(podSeats);
        }

        // Build the list of player pods
        List<List<Player>> playerPods = new ArrayList<>();
        List<Player> currentPod = new ArrayList<>();
        for (Seat seat : seats) {
            currentPod.add(seat.getPlayer());
            if (currentPod.size() == 4) {
                playerPods.add(new ArrayList<>(currentPod));
                currentPod.clear();
            }
        }

        // Count opponent pairings
        Map<UUID, Map<UUID, Integer>> opponentCountMap = new HashMap<>();

        for (List<Player> pod : playerPods) {
            for (int i = 0; i < pod.size(); i++) {
                UUID playerId1 = pod.get(i).getId();
                opponentCountMap.putIfAbsent(playerId1, new HashMap<>());

                for (int j = 0; j < pod.size(); j++) {
                    if (i == j) continue;

                    UUID playerId2 = pod.get(j).getId();
                    opponentCountMap.putIfAbsent(playerId2, new HashMap<>());

                    Map<UUID, Integer> opponents1 = opponentCountMap.get(playerId1);
                    opponents1.put(playerId2, opponents1.getOrDefault(playerId2, 0) + 1);

                    // Now assert that the count doesn't exceed 2
                    int count = opponents1.get(playerId2);
                    assertThat(count)
                            .withFailMessage("Player %s played with opponent %s more than twice", playerId1, playerId2)
                            .isLessThanOrEqualTo(2);
                }
            }
        }
    }
}