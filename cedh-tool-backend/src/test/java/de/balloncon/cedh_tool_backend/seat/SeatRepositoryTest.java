package de.balloncon.cedh_tool_backend.seat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.tournament.Tournament;
import de.balloncon.cedh_tool_backend.tournament.TournamentService;
import de.balloncon.cedh_tool_backend.util.TestDataGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class SeatRepositoryTest {

  @Autowired private SeatService seatService;

  @Autowired private PodService podService;

  @Autowired private PlayerService playerService;

  @Autowired private TournamentService tournamentService;

  @Test
  @Transactional
  void findByPodAndPlayer() {
    Seat seat = new Seat();

    Player player = TestDataGenerator.generatePlayer();
    playerService.save(player);
    assertThat(player.getId()).isNotNull();

    Tournament tournament = TestDataGenerator.generateTournament();
    tournamentService.save(tournament);
    assertThat(tournament.getId()).isNotNull();

    Pod pod = TestDataGenerator.generatePod(tournament);
    podService.save(pod);
    assertThat(pod.getId()).isNotNull();

    seat.setPod(pod);
    seat.setPlayer(player);
    seatService.save(seat);

    Seat seatFromDb = seatService.getByPodIdAndPlayerId(pod.getId(), player.getId());
    assertThat(seatFromDb).isNotNull();
    assertThat(seatFromDb.getPod().getId()).isEqualTo(pod.getId());
    assertThat(seatFromDb.getPlayer().getId()).isEqualTo(player.getId());
  }

}