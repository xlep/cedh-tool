package de.balloncon.cedh_tool_backend.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class ShuffleUtilTest {

  @Autowired
  ShuffleUtil shuffleUtil;
  @Autowired
  private PlayerService playerService;

  @Test
  void testSixtyPlayerShuffle() {
    UUID tournamentId = UUID.fromString("e29fbe3f-1755-43cc-a27a-393ec6d80a09");

    List<Player> playersBeforeShuffle = playerService.getPlayersByTournament(tournamentId);
    List<Player> players = playerService.getPlayersByTournament(tournamentId);

    assertThat(players.size()).isEqualTo(playersBeforeShuffle.size());
    for (Player player : players) {
      assertThat(playersBeforeShuffle.contains(player)).isTrue();
    }

    shuffleUtil.shuffleList(playersBeforeShuffle);

    assertThat(players.size()).isEqualTo(playersBeforeShuffle.size());
    for (Player player : players) {
      assertThat(playersBeforeShuffle.contains(player)).isTrue();
    }
  }

}