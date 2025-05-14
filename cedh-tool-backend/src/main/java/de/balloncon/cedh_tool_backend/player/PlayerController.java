package de.balloncon.cedh_tool_backend.player;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
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

  @PostMapping("player")
  public ResponseEntity<String> createPlayer(@RequestBody PlayerDto playerDto) {
    playerService.savePlayer(playerDto);
    return ResponseEntity.ok("Player created successfully");
  }
}
