package de.balloncon.cedh_tool_backend.player;

import de.balloncon.cedh_tool_backend.pod.PodService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("${apiVersion}")
public class PlayerController {

    private final PlayerService playerService;

    PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("player")
    Player player(@Parameter UUID playerId) {
        return playerService.findPlayerById(playerId);
    }
}
