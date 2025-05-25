package de.balloncon.cedh_tool_backend.tournament.player;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class TournamentPlayerRepositoryTest {

  @Autowired
  private TournamentPlayerRepository tournamentPlayerRepository;

  @Test
  void findPlayersBySStatus() {
    UUID tournamentId = UUID.fromString("1a2b3c4d-5e6f-47a8-8b9c-0d1e2f3a4b5c");
    List<TournamentPlayer> allPlayers = tournamentPlayerRepository
        .findByTournament(tournamentId);

    assertThat(allPlayers)
        .hasSize(10);

    // check query by one status
    List<TournamentPlayer> activePlayers = tournamentPlayerRepository
        .findByTournament(tournamentId, TournamentPlayerStatus.active);

    assertThat(activePlayers)
        .hasSize(4)
        .extracting(tp -> tp.getPlayer().getId())
        .containsExactlyInAnyOrder(
            UUID.fromString("6e0f80a4-2e91-4e4b-9d41-3e5f2b8f7c9a"),
            UUID.fromString("0cd0998e-e235-4783-9ddd-0307985796b8"),
            UUID.fromString("3692448c-928a-4548-8c01-a4d0a05fb3ef"),
            UUID.fromString("8e1e0232-a8eb-4449-8359-bf7a39a9c6d7")
        );

    // check query by status list
    List<TournamentPlayerStatus> inactiveStatus = new ArrayList<>();
    inactiveStatus.add(TournamentPlayerStatus.registered);
    inactiveStatus.add(TournamentPlayerStatus.disqualified);
    inactiveStatus.add(TournamentPlayerStatus.dropped);

    List<TournamentPlayer> inactivePlayers = tournamentPlayerRepository.findByTournament(
        tournamentId, inactiveStatus);

    assertThat(inactivePlayers)
        .hasSize(6)
        .extracting(tp -> tp.getPlayer().getId())
        .containsExactlyInAnyOrder(
            UUID.fromString("710f2392-0b71-4874-8538-f7fa11cf2a28"),
            UUID.fromString("425077e9-a7a7-4fc1-83d1-54636a6bacfc"),
            UUID.fromString("06a96ce1-c9e0-41e1-a031-174de1380c30"),
            UUID.fromString("8b4a2d0b-47c5-4091-91a1-087e4a3d40d7"),
            UUID.fromString("5b23d685-4124-435b-b826-8cc8e8d5f8a1"),
            UUID.fromString("1e70cc4a-decc-46f8-959d-c15798cbc28f")
        );
  }

}