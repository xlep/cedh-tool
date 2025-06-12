package de.balloncon.cedh_tool_backend.tournament;

import static org.assertj.core.api.Assertions.assertThat;

import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.pod.PodType;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerId;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerService;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerStatus;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
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
@Transactional
class TournamentServiceTest {

  @Autowired TournamentService tournamentService;

  @Autowired PodService podService;

  @Autowired TournamentPlayerService tournamentPlayerService;

  @Autowired PlayerService playerService;

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

    Tournament tournament = tournamentService.getTournament(tournamentId);
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

  private Tournament generateTestTournament() {
    Tournament tournament = new Tournament();
    tournament.setMode(TournamentMode.HARERUYA);
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
