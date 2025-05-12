package de.balloncon.cedh_tool_backend.tournament;

import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.seat.Seat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class TournamentServiceTest {

    @Autowired
    TournamentService tournamentService;

    @Autowired
    PodService podService;

    // Test is for 60 player tournaments
    @Test
    public void testNoRepeatedPairingsForFirveRounds() {
        // Use the provided tournament ID
        UUID tournamentId = UUID.fromString("e29fbe3f-1755-43cc-a27a-393ec6d80a09");

        // Track all pairings for uniqueness
        Set<String> pairings = new HashSet<>();

        // Generate 5 rounds
        for (int i = 0; i < 5; i++) {
            // Generate the next round
            tournamentService.generateNextRound(tournamentId);

            // Fetch the updated pods and seats
            List<Pod> pods = podService.getPodsAndSeatsByTournamentId(tournamentId);

            // For each pod, generate pairings and ensure no repeats
            for (Pod pod : pods) {
                for (Seat seat : pod.getSeats()) {
                    for (Seat otherSeat : pod.getSeats()) {
                        if (!seat.equals(otherSeat)) {
                            String pairing = seat.getPlayer().getId() + "-" + otherSeat.getPlayer().getId();
                            // Ensure pairing is stored in consistent order
                            pairings.add(pairing);
                        }
                    }
                }
            }
        }

        // Ensure that no player has been paired with the same opponent more than once
        assertEquals("The number of unique pairings is not equal to the number of pairings generated", pairings.size(), pairings.size());
    }



}