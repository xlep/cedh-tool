package de.balloncon.cedh_tool_backend.tournament.player;

import de.balloncon.cedh_tool_backend.dto.TournamentPlayerDto;
import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${apiVersion}/tournament-players")
@Tag(name = "Tournament Player Controller", description = "Operations related to tournament player management.")
public class TournamentPlayerController {

  private final TournamentPlayerService tournamentPlayerService;


  TournamentPlayerController(TournamentPlayerService tournamentPlayerService
      ) {
    this.tournamentPlayerService = tournamentPlayerService;
  }

  @GetMapping("/tournament/{tournamentId}")
  @Operation(summary = "Get tournament players.", description = "Get tournament players for the tournament with tournamentId.")
  public List<TournamentPlayerDto> getPlayersByTournamentId(@PathVariable UUID tournamentId) {
    return tournamentPlayerService.getPlayersDtosById(tournamentId);
  }

  @PutMapping("/tournament/{tournamentId}/player/{playerId}/status/{status}")
  @Operation(summary = "Set player status.", description = "Set player status for tournament. Used to check-in, drop or disqualify players.")
  public ResponseEntity<Void> setPlayerStatus(
      @PathVariable UUID tournamentId,
      @PathVariable UUID playerId,
      @PathVariable TournamentPlayerStatus status
  ) {
    return tournamentPlayerService.setPlayerStatus(tournamentId, playerId, status);
  }
}