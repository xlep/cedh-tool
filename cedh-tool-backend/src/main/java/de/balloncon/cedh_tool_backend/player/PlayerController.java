package de.balloncon.cedh_tool_backend.player;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.mapper.PlayerMapper;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("${apiVersion}")
public class PlayerController {

  private final PlayerService playerService;
  private final PlayerMapper playerMapper;

  PlayerController(PlayerService playerService, PlayerMapper playerMapper) {
    this.playerService = playerService;
    this.playerMapper = playerMapper;
  }

  @GetMapping("player/{playerId}")
  PlayerDto player(@PathVariable UUID playerId) {
    Player player = playerService.findPlayerById(playerId);
    return playerMapper.toDto(player);
  }

  @PostMapping("player")
  public ResponseEntity<String> createPlayer(@RequestBody PlayerDto playerDto) {
    playerService.savePlayer(playerDto);
    return ResponseEntity.ok("Player created successfully");
  }
}
