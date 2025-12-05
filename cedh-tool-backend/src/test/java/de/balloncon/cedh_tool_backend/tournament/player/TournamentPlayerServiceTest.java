package de.balloncon.cedh_tool_backend.tournament.player;

import static org.assertj.core.api.Assertions.assertThat;

import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.tournament.Tournament;
import de.balloncon.cedh_tool_backend.tournament.TournamentService;
import de.balloncon.cedh_tool_backend.util.TestDataGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class TournamentPlayerServiceTest {

  @Autowired
  private PlayerService playerService;
  @Autowired
  private PodService podService;
  @Autowired
  private TournamentService tournamentService;
  @Autowired
  private TournamentPlayerService tournamentPlayerService;

  private static Pod pod;

  @Test
  void testDropPlayer() {
    Tournament tournament = TestDataGenerator.generateTournament();
    tournamentService.save(tournament);
    Player player = TestDataGenerator.generatePlayer();
    playerService.save(player);
    TournamentPlayer tournamentPlayer = TestDataGenerator.generateTournamentPlayer(tournament,
        player);
    tournamentPlayerService.save(tournamentPlayer);

    TournamentPlayer tournamentPlayerFromDb = tournamentPlayerService.getPlayerById(
        tournament.getId(), player.getId());
    assertThat(tournamentPlayerFromDb).isNotNull();
    assertThat(tournamentPlayerFromDb.getStatus()).isEqualTo(TournamentPlayerStatus.active);

    tournamentPlayerService.setPlayerStatus(tournament.getId(), player.getId(),
        TournamentPlayerStatus.dropped);
    tournamentPlayerService.save(tournamentPlayerFromDb);

    TournamentPlayer tournamentPlayerAfterDrop = tournamentPlayerService.getPlayerById(
        tournament.getId(), player.getId());
    assertThat(tournamentPlayerAfterDrop.getStatus()).isEqualTo(TournamentPlayerStatus.dropped);
  }

  private void setupWinner(int seatNumber) {
    // needed to ensure a commit the transaction before we start
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();
    pod = podService.findByID(pod.getId()).orElseThrow();

    // assign win to player 1, assign losses to all other players
    for (Seat seat : pod.getSeats()) {
      if (seat.getSeat() == seatNumber) {
        seat.setResult(Result.WIN);
      } else {
        seat.setResult(Result.LOSS);
      }
    }
    podService.save(pod);
  }

  private void setupDraw() {
    // needed to ensure a commit the transaction before we start
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();
    pod = podService.findByID(pod.getId()).orElseThrow();

    // assign win to player 1, assign losses to all other players
    for (Seat seat : pod.getSeats()) {
      seat.setResult(Result.DRAW);
    }
    podService.save(pod);
  }
}
