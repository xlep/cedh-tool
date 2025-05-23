package de.balloncon.cedh_tool_backend.player;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.mapper.PlayerMapper;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("${apiVersion}")
@Tag(name = "Player Controller", description = "Operations related to player management.")
public class PlayerController {

  private final PlayerService playerService;
  private final PlayerMapper playerMapper;

  PlayerController(PlayerService playerService, PlayerMapper playerMapper) {
    this.playerService = playerService;
    this.playerMapper = playerMapper;
  }

  @GetMapping("player/{playerId}")
  @Operation(summary = "Used to get player data.", description = "Get the player with playerId.")
  PlayerDto player(@PathVariable UUID playerId) {
    Player player = playerService.findPlayerById(playerId);
    return playerMapper.toDto(player);
  }

  @PostMapping("player")
  @Operation(summary = "Used to save player data.", description = "Save player with the provided data.")
  public ResponseEntity<String> createPlayer(@RequestBody PlayerDto playerDto) {
    playerService.savePlayer(playerDto);
    return ResponseEntity.ok("Player created successfully");
  }
}
