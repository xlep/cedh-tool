package de.balloncon.cedh_tool_backend.seat;

import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerRepository;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodRepository;
import de.balloncon.cedh_tool_backend.tournament.Tournament;
import de.balloncon.cedh_tool_backend.tournament.TournamentRepository;
import de.balloncon.cedh_tool_backend.util.TestDataGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class SeatRepositoryTest {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private PodRepository podRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TournamentRepository tournamentRepository;


    @Test
    @Transactional
    void findByPodAndPlayer() {
        Seat seat = new Seat();

        Player player = TestDataGenerator.generatePlayer();
        playerRepository.save(player);
        assertThat(player.getId()).isNotNull();

        Tournament tournament = TestDataGenerator.generateTournament();
        tournamentRepository.save(tournament);
        assertThat(tournament.getId()).isNotNull();

        Pod pod = TestDataGenerator.generatePod(tournament);
        podRepository.save(pod);
        assertThat(pod.getId()).isNotNull();

        seat.setPod(pod);
        seat.setPlayer(player);
        seatRepository.save(seat);

        Seat seatFromDb = seatRepository.findByPodAndPlayer(pod.getId(), player.getId());
        assertThat(seatFromDb).isNotNull();
        assertThat(seatFromDb.getPod().getId()).isEqualTo(pod.getId());
        assertThat(seatFromDb.getPlayer().getId()).isEqualTo(player.getId());
    }

}