package de.balloncon.cedh_tool_backend.util;

import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerRepository;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
class ShuffleUtilTest {

  @Autowired
  TournamentPlayerRepository tournamentPlayerRepository;

  @Autowired
  ShuffleUtil shuffleUtil;

  @Test
  void testSixtyPlayerShuffle() {
    UUID tournamentId = UUID.fromString("e29fbe3f-1755-43cc-a27a-393ec6d80a09");

    List<Player> playersBeforeShuffle = tournamentPlayerRepository.findByTournamentId(tournamentId);
    List<Player> players = tournamentPlayerRepository.findByTournamentId(tournamentId);

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