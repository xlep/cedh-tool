package de.balloncon.cedh_tool_backend.tournament.player;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.tournament.player.score.view.TournamentPlayerScoreView;
import io.swagger.v3.oas.annotations.Parameter;
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

  @GetMapping("tournament/players/score")
  public List<TournamentPlayerScoreView> getPlayerScoresByTournament(
      @RequestParam UUID tournamentId) {
    return tournamentPlayerService.getPlayerScoresByTournamentId(tournamentId);
  }

  @GetMapping("tournament/players")
  public List<PlayerDto> getPlayersByTournamentId(@Parameter UUID tournamentId) {
    return tournamentPlayerService.getPlayersById(tournamentId);
  }
}
