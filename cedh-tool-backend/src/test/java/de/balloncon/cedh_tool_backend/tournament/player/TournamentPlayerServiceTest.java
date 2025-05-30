package de.balloncon.cedh_tool_backend.tournament.player;

import static org.assertj.core.api.Assertions.assertThat;

import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerRepository;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodRepository;
import de.balloncon.cedh_tool_backend.pod.PodType;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.seat.SeatRepository;
import de.balloncon.cedh_tool_backend.tournament.Tournament;
import de.balloncon.cedh_tool_backend.tournament.TournamentRepository;
import de.balloncon.cedh_tool_backend.tournament.TournamentService;
import de.balloncon.cedh_tool_backend.util.TestDataGenerator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.assertj.core.groups.Tuple;
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
  private PlayerRepository playerRepository;
  @Autowired
  private PodRepository podRepository;
  @Autowired
  private SeatRepository seatRepository;
  @Autowired
  private TournamentPlayerRepository tournamentPlayerRepository;
  @Autowired
  private TournamentRepository tournamentRepository;

  @Autowired
  private TournamentPlayerService tournamentPlayerService;

  private static Pod pod;
  @Autowired
  private TournamentService tournamentService;

  @Test
  void testDropPlayer() {
    Tournament tournament = TestDataGenerator.generateTournament();
    tournamentRepository.save(tournament);
    Player player = TestDataGenerator.generatePlayer();
    playerRepository.save(player);
    TournamentPlayer tournamentPlayer = TestDataGenerator.generateTournamentPlayer(tournament,
        player);
    tournamentPlayerRepository.save(tournamentPlayer);

    TournamentPlayer tournamentPlayerFromDb = tournamentPlayerRepository.findByTournamentAndPlayer(
        tournament.getId(), player.getId());
    assertThat(tournamentPlayerFromDb).isNotNull();
    assertThat(tournamentPlayerFromDb.getStatus()).isEqualTo(TournamentPlayerStatus.active);

    tournamentPlayerService.setPlayerStatus(tournament.getId(), player.getId(),
        TournamentPlayerStatus.dropped);
    tournamentPlayerRepository.save(tournamentPlayerFromDb);

    TournamentPlayer tournamentPlayerAfterDrop = tournamentPlayerRepository.findByTournamentAndPlayer(
        tournament.getId(), player.getId());
    assertThat(tournamentPlayerAfterDrop.getStatus()).isEqualTo(TournamentPlayerStatus.dropped);
  }

  private void setupWinner(int seatNumber) {
    // needed to ensure a commit the transaction before we start
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();
    pod = podRepository.findById(pod.getId()).orElseThrow();

    // assign win to player 1, assign losses to all other players
    for (Seat seat : pod.getSeats()) {
      if (seat.getSeat() == seatNumber) {
        seat.setResult(Result.win);
      } else {
        seat.setResult(Result.loss);
      }
    }
    podRepository.save(pod);
  }

  private void setupDraw() {
    // needed to ensure a commit the transaction before we start
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();
    pod = podRepository.findById(pod.getId()).orElseThrow();

    // assign win to player 1, assign losses to all other players
    for (Seat seat : pod.getSeats()) {
      seat.setResult(Result.draw);
    }
    podRepository.save(pod);
  }
}
