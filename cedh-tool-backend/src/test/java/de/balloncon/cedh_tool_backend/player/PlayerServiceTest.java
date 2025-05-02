package de.balloncon.cedh_tool_backend.player;

import de.balloncon.cedh_tool_backend.CedhToolBackendApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PlayerServiceTest {

    @Autowired
    PlayerService playerService;

    @Test
    void getAllPlayers() {
        List<Player> allPlayers = playerService.getAllPlayers();

        assertThat(allPlayers.size())
                .isEqualTo(2);

        assertThat(allPlayers)
                .filteredOn(player -> player.getNickname().equals("Don")
                        && player.getFirstName().equals("Donald")
                        && player.getLastName().equals("Duck"));

        assertThat(allPlayers)
                .filteredOn(player -> player.getNickname().equals("DD"));
    }

    @Test
    void getPlayerById() {
        Player player = playerService.findPlayerById("01969318-8ded-7d09-b9a3-5fd88be2e0f8");

        assertThat(player).isNotNull();
        assertThat(player.getNickname()).isEqualTo("DD");
        assertThat(player.getFirstName()).isEqualTo("Daisy");
        assertThat(player.getLastName()).isEqualTo("Duck");
    }

}