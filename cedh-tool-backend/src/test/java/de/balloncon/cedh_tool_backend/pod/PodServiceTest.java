package de.balloncon.cedh_tool_backend.pod;

import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerRepository;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.seat.SeatRepository;
import de.balloncon.cedh_tool_backend.tournament.Tournament;
import de.balloncon.cedh_tool_backend.tournament.TournamentRepository;
import de.balloncon.cedh_tool_backend.util.TestDataGenerator;
import jakarta.transaction.Transactional;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.transaction.TestTransaction;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class PodServiceTest {

    @Autowired
    private PodService podService;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    PodRepository podRepository;

    @Autowired
    SeatRepository seatRepository;

    
    @Test
    void reportWin() {
        Player player1 = TestDataGenerator.generatePlayer();
        playerRepository.save(player1);
        Player player2 = TestDataGenerator.generatePlayer();
        playerRepository.save(player2);
        Player player3 = TestDataGenerator.generatePlayer();
        playerRepository.save(player3);
        Player player4 = TestDataGenerator.generatePlayer();
        playerRepository.save(player4);

        Tournament tournament = TestDataGenerator.generateTournament();
        tournamentRepository.save(tournament);
        Pod pod = TestDataGenerator.generatePod(tournament);
        podRepository.save(pod);

        Seat seat1 = TestDataGenerator.generateSeatWithoutResult(pod, player1);
        seatRepository.save(seat1);
        Seat seat2 = TestDataGenerator.generateSeatWithoutResult(pod, player2);
        seatRepository.save(seat2);
        Seat seat3 = TestDataGenerator.generateSeatWithoutResult(pod, player3);
        seatRepository.save(seat3);
        Seat seat4 = TestDataGenerator.generateSeatWithoutResult(pod, player4);
        seatRepository.save(seat4);    

        List<Seat> byPodId = seatRepository.findByPodId(pod.getId());

        // needed to ensure a commit the transaction before we start 
        TestTransaction.flagForCommit();
        TestTransaction.end();
        TestTransaction.start();

        // report win for player1
        podService.reportResult(pod.getId(), player1.getId(), Result.win);

        Pod podFromDb = podService.getPodById(pod.getId());
        assertThat(podFromDb.getSeats())
                .hasSize(4)
                .extracting(
                    seat -> seat.getPlayer().getId(),
                        Seat::getResult)
                .containsExactlyInAnyOrder(
                    new Tuple(player1.getId(), Result.win.toString()),
                    new Tuple(player2.getId(), Result.loss.toString()),
                    new Tuple(player3.getId(), Result.loss.toString()),
                    new Tuple(player4.getId(), Result.loss.toString())
                );
    }

    @Test
    void reportDraw() {
        Player player1 = TestDataGenerator.generatePlayer();
        playerRepository.save(player1);
        Player player2 = TestDataGenerator.generatePlayer();
        playerRepository.save(player2);
        Player player3 = TestDataGenerator.generatePlayer();
        playerRepository.save(player3);
        Player player4 = TestDataGenerator.generatePlayer();
        playerRepository.save(player4);

        Tournament tournament = TestDataGenerator.generateTournament();
        tournamentRepository.save(tournament);
        Pod pod = TestDataGenerator.generatePod(tournament);
        podRepository.save(pod);

        Seat seat1 = TestDataGenerator.generateSeatWithoutResult(pod, player1);
        seatRepository.save(seat1);
        Seat seat2 = TestDataGenerator.generateSeatWithoutResult(pod, player2);
        seatRepository.save(seat2);
        Seat seat3 = TestDataGenerator.generateSeatWithoutResult(pod, player3);
        seatRepository.save(seat3);
        Seat seat4 = TestDataGenerator.generateSeatWithoutResult(pod, player4);
        seatRepository.save(seat4);

        List<Seat> byPodId = seatRepository.findByPodId(pod.getId());

        // needed to ensure a commit the transaction before we start
        TestTransaction.flagForCommit();
        TestTransaction.end();
        TestTransaction.start();

        // report win for player1
        podService.reportResult(pod.getId(), player1.getId(), Result.draw);

        Pod podFromDb = podService.getPodById(pod.getId());
        assertThat(podFromDb.getSeats())
                .hasSize(4)
                .extracting(
                        seat -> seat.getPlayer().getId(),
                        Seat::getResult)
                .containsExactlyInAnyOrder(
                        new Tuple(player1.getId(), Result.draw.toString()),
                        new Tuple(player2.getId(), Result.draw.toString()),
                        new Tuple(player3.getId(), Result.draw.toString()),
                        new Tuple(player4.getId(), Result.draw.toString())
                );
    }

}