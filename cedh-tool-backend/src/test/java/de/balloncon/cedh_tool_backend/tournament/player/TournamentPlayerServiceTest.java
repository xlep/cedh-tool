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

  @Autowired private PlayerRepository playerRepository;
  @Autowired private PodRepository podRepository;
  @Autowired private SeatRepository seatRepository;
  @Autowired private TournamentPlayerRepository tournamentPlayerRepository;
  @Autowired private TournamentRepository tournamentRepository;

  @Autowired private TournamentPlayerService tournamentPlayerService;

  private static Tournament tournament;
  private static Pod pod;


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
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService
            .calculatePlayerScoresAfterSwissRounds(tournament.getId(), 1);

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
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService
            .calculatePlayerScoresAfterSwissRounds(tournament.getId(), 1);

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
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService
            .calculatePlayerScoresAfterSwissRounds(tournament.getId(),1);

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
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService
            .calculatePlayerScoresAfterSwissRounds(tournament.getId(), 1);

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
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService
            .calculatePlayerScoresAfterSwissRounds(tournament.getId(), 1);

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

  @Test
  void TestRoundPermutations() {

    List<Integer> rounds = List.of(1, 2, 3, 4, 5);
    List<List<Integer>> permutations = tournamentPlayerService.generatePermutations(rounds);

    assertThat(permutations)
            .hasSize(120)
            .contains(List.of(1, 2, 3, 5, 4), List.of(5, 1, 2, 3, 4));
  }

}
