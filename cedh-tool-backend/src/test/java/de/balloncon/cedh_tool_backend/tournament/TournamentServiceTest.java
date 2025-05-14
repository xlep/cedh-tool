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

  @Autowired TournamentService tournamentService;

  @Autowired PodService podService;

  // Test is for 60 player tournaments
  @Test
  public void testNoRepeatedPairingsForFiveRounds() {
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

    // For each pod, generate pairings and ensure no repeats
    for (Pod pod : pods) {
      ArrayList<Seat> seats = new ArrayList<>(pod.getSeats());
      for (int i = 0; i < seats.size(); i++) {
        for (int j = i + 1; j < seats.size(); j++) {
          String pairing = sortPair(seats.get(i).getPlayer().getId().toString(),
                  seats.get(j).getPlayer().getId().toString());
          // Ensure pairing is stored in consistent order
          addPairingsResult = pairings.add(pairing);
          prairingsList.add(pairing);
          assertEquals(String.format("Double Pairing detected. Found pairing duplicate %s", pairing),
                  true, addPairingsResult);
        }
      }
    }


    // Ensure that no player has been paired with the same opponent more than once
    // TODO: does not mean that there are no repeats - either check the result of .add() or count the permutations and compare to .size()
    assertEquals(
        "The number of unique pairings is not equal to the number of pairings generated",
        pairings.size(),
        pairings.size());
  }

  private static String sortPair(String id1, String id2) {
    List<String> pairings = Arrays.asList(id1, id2);
    Collections.sort(pairings);

    return String.join(":", pairings);
  }
}
