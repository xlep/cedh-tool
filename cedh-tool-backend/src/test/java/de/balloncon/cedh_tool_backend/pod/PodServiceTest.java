package de.balloncon.cedh_tool_backend.pod;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.seat.SeatService;
import de.balloncon.cedh_tool_backend.tournament.Tournament;
import de.balloncon.cedh_tool_backend.tournament.TournamentService;
import de.balloncon.cedh_tool_backend.util.TestDataGenerator;
import jakarta.transaction.Transactional;
import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.transaction.TestTransaction;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class PodServiceTest {

  @Autowired
  private PodService podService;

  @Autowired private PlayerService playerService;

  @Autowired private TournamentService tournamentService;

  @Autowired private SeatService seatService;

  @Test
  void reportWinForSwiss() {
    Player player1 = TestDataGenerator.generatePlayer();
    playerService.save(player1);
    Player player2 = TestDataGenerator.generatePlayer();
    playerService.save(player2);
    Player player3 = TestDataGenerator.generatePlayer();
    playerService.save(player3);
    Player player4 = TestDataGenerator.generatePlayer();
    playerService.save(player4);

    Tournament tournament = TestDataGenerator.generateTournament();
    tournamentService.save(tournament);
    Pod pod = TestDataGenerator.generatePod(tournament);
    pod.setType(PodType.SWISS);
    podService.save(pod);

    Seat seat1 = TestDataGenerator.generateSeatWithoutResult(pod, player1);
    seatService.save(seat1);
    Seat seat2 = TestDataGenerator.generateSeatWithoutResult(pod, player2);
    seatService.save(seat2);
    Seat seat3 = TestDataGenerator.generateSeatWithoutResult(pod, player3);
    seatService.save(seat3);
    Seat seat4 = TestDataGenerator.generateSeatWithoutResult(pod, player4);
    seatService.save(seat4);

    List<Seat> byPodId = seatService.getByPodID(pod.getId());

    // needed to ensure a commit the transaction before we start
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();

    // report win for player1
    podService.reportResult(pod.getId(), player1.getId(), Result.WIN);

    Pod podFromDb = podService.getPodById(pod.getId());
    assertThat(podFromDb.getSeats())
        .hasSize(4)
        .extracting(seat -> seat.getPlayer().getId(), Seat::getResult)
        .containsExactlyInAnyOrder(
            new Tuple(player1.getId(), Result.WIN),
            new Tuple(player2.getId(), Result.LOSS),
            new Tuple(player3.getId(), Result.LOSS),
            new Tuple(player4.getId(), Result.LOSS));
  }

  @Test
  void reportDrawForSwiss() {
    Player player1 = TestDataGenerator.generatePlayer();
    playerService.save(player1);
    Player player2 = TestDataGenerator.generatePlayer();
    playerService.save(player2);
    Player player3 = TestDataGenerator.generatePlayer();
    playerService.save(player3);
    Player player4 = TestDataGenerator.generatePlayer();
    playerService.save(player4);

    Tournament tournament = TestDataGenerator.generateTournament();
    tournamentService.save(tournament);
    Pod pod = TestDataGenerator.generatePod(tournament);
    podService.save(pod);

    Seat seat1 = TestDataGenerator.generateSeatWithoutResult(pod, player1);
    seatService.save(seat1);
    Seat seat2 = TestDataGenerator.generateSeatWithoutResult(pod, player2);
    seatService.save(seat2);
    Seat seat3 = TestDataGenerator.generateSeatWithoutResult(pod, player3);
    seatService.save(seat3);
    Seat seat4 = TestDataGenerator.generateSeatWithoutResult(pod, player4);
    seatService.save(seat4);

    List<Seat> byPodId = seatService.getByPodID(pod.getId());

    // needed to ensure a commit the transaction before we start
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();

    // report win for player1
    podService.reportResult(pod.getId(), player1.getId(), Result.DRAW);

    Pod podFromDb = podService.getPodById(pod.getId());
    assertThat(podFromDb.getSeats())
        .hasSize(4)
        .extracting(seat -> seat.getPlayer().getId(), Seat::getResult)
        .containsExactlyInAnyOrder(
            new Tuple(player1.getId(), Result.DRAW),
            new Tuple(player2.getId(), Result.DRAW),
            new Tuple(player3.getId(), Result.DRAW),
            new Tuple(player4.getId(), Result.DRAW));
  }

  @Test
  void reportWinForSemifinal() {
    // data for semifinals
    Player player1 = TestDataGenerator.generatePlayer();
    playerService.save(player1);
    Player player2 = TestDataGenerator.generatePlayer();
    playerService.save(player2);
    Player player3 = TestDataGenerator.generatePlayer();
    playerService.save(player3);
    Player player4 = TestDataGenerator.generatePlayer();
    playerService.save(player4);

    Tournament tournament = TestDataGenerator.generateTournament();
    tournamentService.save(tournament);
    Pod pod = TestDataGenerator.generatePod(tournament);
    pod.setType(PodType.SEMIFINAL);
    podService.save(pod);

    Seat seat1 = TestDataGenerator.generateSeatWithoutResult(pod, player1);
    seatService.save(seat1);
    Seat seat2 = TestDataGenerator.generateSeatWithoutResult(pod, player2);
    seatService.save(seat2);
    Seat seat3 = TestDataGenerator.generateSeatWithoutResult(pod, player3);
    seatService.save(seat3);
    Seat seat4 = TestDataGenerator.generateSeatWithoutResult(pod, player4);
    seatService.save(seat4);

    List<Seat> byPodId = seatService.getByPodID(pod.getId());

    // data for finals
    Player player7 = TestDataGenerator.generatePlayer();
    playerService.save(player7);
    Player player8 = TestDataGenerator.generatePlayer();
    playerService.save(player8);
    Pod podForFinals = TestDataGenerator.generatePod(tournament);
    podForFinals.setType(PodType.FINAL);
    podService.save(podForFinals);

    // needed to ensure a commit the transaction before we start
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();

    // report win
    podService.reportResult(pod.getId(), player1.getId(), Result.WIN);

    Pod podFromDb = podService.getPodById(pod.getId());
    assertThat(podFromDb.getSeats())
        .hasSize(4)
        .extracting(seat -> seat.getPlayer().getId(), Seat::getResult)
        .containsExactlyInAnyOrder(
            new Tuple(player1.getId(), Result.WIN),
            new Tuple(player2.getId(), Result.LOSS),
            new Tuple(player3.getId(), Result.LOSS),
            new Tuple(player4.getId(), Result.LOSS));
    Pod finalsPod = podService.getPodById(podForFinals.getId());

    assertThat(finalsPod.getSeats().size() == 3);
    assertThat(finalsPod.getSeats().contains(player1));
  }

  @Test
  void reportWinForFinals() {
    Player player1 = TestDataGenerator.generatePlayer();
    playerService.save(player1);
    Player player2 = TestDataGenerator.generatePlayer();
    playerService.save(player2);
    Player player3 = TestDataGenerator.generatePlayer();
    playerService.save(player3);
    Player player4 = TestDataGenerator.generatePlayer();
    playerService.save(player4);
    Tournament tournament = TestDataGenerator.generateTournament();
    tournamentService.save(tournament);
    Pod pod = TestDataGenerator.generatePod(tournament);
    pod.setType(PodType.FINAL);

    podService.save(pod);
    Seat seat1 = TestDataGenerator.generateSeatWithoutResult(pod, player1);
    seatService.save(seat1);
    Seat seat2 = TestDataGenerator.generateSeatWithoutResult(pod, player2);
    seatService.save(seat2);
    Seat seat3 = TestDataGenerator.generateSeatWithoutResult(pod, player3);
    seatService.save(seat3);
    Seat seat4 = TestDataGenerator.generateSeatWithoutResult(pod, player4);
    seatService.save(seat4);
    List<Seat> byPodId = seatService.getByPodID(pod.getId());

    // needed to ensure a commit the transaction before we start
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();

    // report win
    podService.reportResult(pod.getId(), player1.getId(), Result.WIN);

    Pod podFromDb = podService.getPodById(pod.getId());
    assertThat(podFromDb.getSeats())
        .hasSize(4)
        .extracting(seat -> seat.getPlayer().getId(), Seat::getResult)
        .containsExactlyInAnyOrder(
            new Tuple(player1.getId(), Result.WIN),
            new Tuple(player2.getId(), Result.LOSS),
            new Tuple(player3.getId(), Result.LOSS),
            new Tuple(player4.getId(), Result.LOSS));
  }
}
