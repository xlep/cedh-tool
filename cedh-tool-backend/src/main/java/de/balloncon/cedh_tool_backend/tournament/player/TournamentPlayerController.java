package de.balloncon.cedh_tool_backend.tournament.player;

import de.balloncon.cedh_tool_backend.dto.TournamentPlayerDto;
import de.balloncon.cedh_tool_backend.tournament.player.score.view.TournamentPlayerScoreView;
import de.balloncon.cedh_tool_backend.tournament.player.score.view.TournamentPlayerScoreViewFactory;
import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("${apiVersion}/tournament-players")
@Tag(name = "Tournament Player Controller", description = "Operations related to tournament player management.")
public class TournamentPlayerController {

  private final TournamentPlayerService tournamentPlayerService;
  private final TournamentPlayerScoreViewFactory tournamentPlayerScoreViewFactory;

  TournamentPlayerController(TournamentPlayerService tournamentPlayerService,
      TournamentPlayerScoreViewFactory scoreViewFactory) {
    this.tournamentPlayerService = tournamentPlayerService;
    this.tournamentPlayerScoreViewFactory = scoreViewFactory;
  }

  @GetMapping("/tournament/{tournamentId}/round/{round}/scores")
  @Operation(summary = "Get tournament player scores.", description = "Creates tournament player scores for the specified round number.")
  public List<TournamentPlayerScoreView> getPlayerScoresByTournamentRound(
      @PathVariable int round,
      @PathVariable UUID tournamentId) {
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService
        .calculatePlayerScoresAfterSwissRounds(tournamentId, round);
    return tournamentPlayerScoreViewFactory.createFromTournamentPlayerList(tournamentPlayers);
  }

  @GetMapping("/tournament/{tournamentId}")
  @Operation(summary = "Get tournament players.", description = "Get tournament players for the tournament with tournamentId.")
  public List<TournamentPlayerDto> getPlayersByTournamentId(@PathVariable UUID tournamentId) {
    return tournamentPlayerService.getPlayersDtosById(tournamentId);
  }

  @PutMapping("/tournament/{tournamentId}/player/{playerId}/status/{status}")
  @Operation(summary = "Set player status.", description = "Set player status for tournament. Used to check-in, drop or disqualify players..")
  public ResponseEntity<Void> setPlayerStatus(
      @PathVariable UUID tournamentId,
      @PathVariable UUID playerId,
      @PathVariable TournamentPlayerStatus status
  ) {
    return tournamentPlayerService.setPlayerStatus(tournamentId, playerId, status);
  }

  // TODO: url should look like this /api/v1/tournament/{tournamentId}/player/{playerId}/table_lock/{tableNumber} (PUT)
  @PutMapping("/table_lock")
  @Operation(summary = "Locks player to a table", description = "Locks player to a table (a.k.a. pod number) for the remainder of the rounds. Mainly for purposes of accessibility.")
  public ResponseEntity<Void> lockPlayerTable(
      @RequestParam UUID tournamentId,
      @RequestParam UUID playerId,
      @RequestParam Integer tableNumber
  ) {
    return tournamentPlayerService.lockPlayerToTable(tournamentId, playerId, tableNumber);
  }


}