package de.balloncon.cedh_tool_backend.tournament.player;

import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerRepository;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodRepository;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.seat.SeatRepository;
import de.balloncon.cedh_tool_backend.tournament.Tournament;
import de.balloncon.cedh_tool_backend.tournament.TournamentRepository;
import de.balloncon.cedh_tool_backend.tournament.TournamentService;
import de.balloncon.cedh_tool_backend.util.TestDataGenerator;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class TournamentPlayerServiceTest {

  @Autowired private TournamentRepository tournamentRepository;

  @Autowired private PlayerRepository playerRepository;

  @Autowired private TournamentPlayerRepository tournamentPlayerRepository;

  private static Tournament tournament;
  private static Pod pod;

  @Autowired private TournamentPlayerService tournamentPlayerService;
    @Autowired
    private PodRepository podRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private TournamentService tournamentService;

  void setupTournamentWithOnePod() {
    tournament = TestDataGenerator.generateTournament();
    tournamentRepository.save(tournament);

    pod = TestDataGenerator.generatePod(tournament);
    podRepository.save(pod);


    for (int i = 1; i <= 4; i++) {
      Player player = TestDataGenerator.generatePlayer("Player" + i);
      playerRepository.save(player);

      Seat seat = TestDataGenerator.generateSeatWithoutResult(pod, player, i);
      seatRepository.save(seat);

      TournamentPlayer tournamentPlayer = TestDataGenerator.generateTournamentPlayer(tournament, player);
      tournamentPlayerRepository.save(tournamentPlayer);
    }
  }

  @Test
  void testSeat1Wins() {
    setupTournamentWithOnePod();
    setupWinner(1);

    // -- end test setup

    // calculate scores
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService.calculatePlayerScores(tournament.getId());

    // check points
    assertThat(tournamentPlayers)
            .hasSize(4)
            .extracting(tp -> tp.getPlayer().getNickname(),
                    TournamentPlayer::getScore)
            .containsExactlyInAnyOrder(
                    new Tuple("Player1", new BigDecimal("1781.40000")),
                    new Tuple("Player2", new BigDecimal("1389.75000")),
                    new Tuple("Player3", new BigDecimal("1404.45000")),
                    new Tuple("Player4", new BigDecimal("1424.40000"))
            );
  }

  @Test
  void testSeat2Wins() {
    setupTournamentWithOnePod();
    setupWinner(2);

    // -- end test setup

    // calculate scores
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService.calculatePlayerScores(tournament.getId());

    // check points
    assertThat(tournamentPlayers)
            .hasSize(4)
            .extracting(tp -> tp.getPlayer().getNickname(),
                    TournamentPlayer::getScore)
            .containsExactlyInAnyOrder(
                    new Tuple("Player1", new BigDecimal("1361.40000")),
                    new Tuple("Player2", new BigDecimal("1809.75000")),
                    new Tuple("Player3", new BigDecimal("1404.45000")),
                    new Tuple("Player4", new BigDecimal("1424.40000"))
            );
  }

  @Test
  void testSeat3Wins() {
    setupTournamentWithOnePod();
    setupWinner(3);

    // -- end test setup

    // calculate scores
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService.calculatePlayerScores(tournament.getId());

    // check points
    assertThat(tournamentPlayers)
            .hasSize(4)
            .extracting(tp -> tp.getPlayer().getNickname(),
                    TournamentPlayer::getScore)
            .containsExactlyInAnyOrder(
                    new Tuple("Player1", new BigDecimal("1361.40000")),
                    new Tuple("Player2", new BigDecimal("1389.75000")),
                    new Tuple("Player3", new BigDecimal("1824.45000")),
                    new Tuple("Player4", new BigDecimal("1424.40000"))
            );
  }

  @Test
  void testSeat4Wins() {
    setupTournamentWithOnePod();
    setupWinner(4);

    // -- end test setup

    // calculate scores
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService.calculatePlayerScores(tournament.getId());

    // check points
    assertThat(tournamentPlayers)
            .hasSize(4)
            .extracting(tp -> tp.getPlayer().getNickname(),
                    TournamentPlayer::getScore)
            .containsExactlyInAnyOrder(
                    new Tuple("Player1", new BigDecimal("1361.40000")),
                    new Tuple("Player2", new BigDecimal("1389.75000")),
                    new Tuple("Player3", new BigDecimal("1404.45000")),
                    new Tuple("Player4", new BigDecimal("1844.40000"))
            );
  }

  @Test
  void testDraw() {
    setupTournamentWithOnePod();
    setupDraw();

    // -- end test setup

    // calculate scores
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService.calculatePlayerScores(tournament.getId());

    // check points
    assertThat(tournamentPlayers)
            .hasSize(4)
            .extracting(tp -> tp.getPlayer().getNickname(),
                    TournamentPlayer::getScore)
            .containsExactlyInAnyOrder(
                    new Tuple("Player1", new BigDecimal("1466.40000")),
                    new Tuple("Player2", new BigDecimal("1494.75000")),
                    new Tuple("Player3", new BigDecimal("1509.45000")),
                    new Tuple("Player4", new BigDecimal("1529.40000"))
            );
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
        seat.setResult(Result.win.toString());
      } else {
        seat.setResult(Result.loss.toString());
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
      seat.setResult(Result.draw.toString());
    }
    podRepository.save(pod);
  }
//
//  @Test
//  void testSeat2Wins() {
//    runAndAssertScoreForWinnerSeat(2);
//  }
//
//  @Test
//  void testSeat3Wins() {
//    runAndAssertScoreForWinnerSeat(3);
//  }
//
//  @Test
//  void testSeat4Wins() {
//    runAndAssertScoreForWinnerSeat(4);
//  }

//  private void runAndAssertScoreForWinnerSeat(int winningSeat) {
//    Player winner = players.get(winningSeat - 1);
//    UUID winnerId = winner.getId();
//
//    tournamentPlayerService.calculateAndAssignNewScores(
//        tournament.getId(), new HashMap<>(seatMap), winnerId);
//
//    List<TournamentPlayer> updatedPlayers =
//        tournamentPlayerRepository.findByTournamentAndPlayers(
//            tournament.getId(), players.stream().map(p -> p.getId().toString()).toList());
//
//    BigDecimal totalContribution = BigDecimal.ZERO;
//    for (TournamentPlayer tp : updatedPlayers) {
//      if (!tp.getPlayer().getId().equals(winnerId)) {
//        assertThat(tp.getScore()).isLessThan(new BigDecimal("1500.000"));
//        totalContribution =
//            totalContribution.add(new BigDecimal("1500.000").subtract(tp.getScore()));
//      }
//    }
//
//    TournamentPlayer winningPlayer =
//        updatedPlayers.stream()
//            .filter(tp -> tp.getPlayer().getId().equals(winnerId))
//            .findFirst()
//            .orElseThrow();
//
//    assertThat(winningPlayer.getScore())
//        .isEqualByComparingTo(new BigDecimal("1500.000").add(totalContribution));
//  }

}
