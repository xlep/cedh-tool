package de.balloncon.cedh_tool_backend.tournament.player;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.tournament.player.score.view.TournamentPlayerScoreView;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("${apiVersion}")
public class TournamentPlayerController {

  private final TournamentPlayerService tournamentPlayerService;

  TournamentPlayerController(TournamentPlayerService tournamentPlayerService) {
    this.tournamentPlayerService = tournamentPlayerService;
  }

  @GetMapping("tournament/players/score/{tournamentId}")
  public List<TournamentPlayerScoreView> getPlayerScoresByTournament(
      @PathVariable UUID tournamentId) {
    return tournamentPlayerService.getPlayerScoresByTournamentId(tournamentId);
  }

  @GetMapping("tournament/players/{tournamentId}")
  public List<PlayerDto> getPlayersByTournamentId(@PathVariable UUID tournamentId) {
    return tournamentPlayerService.getPlayersById(tournamentId);
  }
}
