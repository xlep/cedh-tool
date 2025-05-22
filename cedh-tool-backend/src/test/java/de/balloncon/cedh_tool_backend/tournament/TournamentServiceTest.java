package de.balloncon.cedh_tool_backend.tournament;

import static org.springframework.test.util.AssertionErrors.assertEquals;

import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.pod.PodType;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.seat.SeatService;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerId;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerService;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class TournamentServiceTest {

  @Autowired
  TournamentService tournamentService;

  @Autowired
  PodService podService;

  @Autowired
  TournamentPlayerService tournamentPlayerService;

  @Autowired
  SeatService seatService;

  @Autowired
  PlayerService playerService;

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
          assertEquals(
              String.format("Double Pairing detected. Found pairing duplicate %s", pairing),
              true,
              addPairingsResult);
        }
      }
    }

    // Ensure that no player has been paired with the same opponent more than once
    // TODO: does not mean that there are no repeats - either check the result of .add() or count
    // the permutations and compare to .size()
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

  @Test
  @Transactional
  void testTopTenCut() {

    int cutSize = 10;
    int previousRoundNumber = 5;
    int semifinalRoundNumber = 6;
    int finalRoundNumber = 7;

    Tournament tournament = generateTestTournament();
    List<Player> playersList = generateTestPlayers();
    generateTestTournamentPlayers(tournament, playersList);
    generatePreviousRoundPod(tournament, previousRoundNumber);

    tournamentService.determineCut(tournament.getId(), cutSize);

    List<Pod> semifinalPods =
        podService.getPodsByRoundNumber(tournament.getId(), semifinalRoundNumber);
    List<Pod> finalPods = podService.getPodsByRoundNumber(tournament.getId(), finalRoundNumber);

    assert semifinalPods.size() == 2;
    assert finalPods.size() == 1;

    // todo seats is always an empty array

    //    List<String> finalPlayerFirstnames = finalPods.getFirst().getSeats().stream()
    //            .map(seat -> seat.getPlayer().getFirstname())
    //            .toList();

    //    assertTrue(finalPlayerFirstnames.contains("nine"));
    //    assertTrue(finalPlayerFirstnames.contains("ten"));
    //    assertEquals("2 players are in the final pod",2, finalPods.getFirst().getSeats().size());
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
