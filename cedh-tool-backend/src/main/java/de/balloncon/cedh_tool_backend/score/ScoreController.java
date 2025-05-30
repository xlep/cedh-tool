package de.balloncon.cedh_tool_backend.score;

import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import de.balloncon.cedh_tool_backend.tournament.player.score.view.TournamentPlayerScoreView;
import de.balloncon.cedh_tool_backend.tournament.player.score.view.TournamentPlayerScoreViewFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${apiVersion}/score")
@Tag(name = "Score Controller", description = "Operations related to tournament player score.")
public class ScoreController {

  private final TournamentPlayerScoreViewFactory tournamentPlayerScoreViewFactory;
  private final ScoreService scoreService;

  ScoreController(TournamentPlayerScoreViewFactory tournamentPlayerScoreViewFactory,ScoreService scoreService) {
    this.tournamentPlayerScoreViewFactory = tournamentPlayerScoreViewFactory;
    this.scoreService = scoreService;
  }

  @GetMapping("/tournament/{tournamentId}/round/{round}")
  @Operation(summary = "Get tournament player scores.", description = "Creates tournament player scores for the specified round number.")
  public List<TournamentPlayerScoreView> getPlayerScoresByTournamentRound(
      @PathVariable int round,
      @PathVariable UUID tournamentId) {
    List<TournamentPlayer> tournamentPlayers = scoreService
        .calculatePlayerScoresAfterSwissRounds(tournamentId, round);
    return tournamentPlayerScoreViewFactory.createFromTournamentPlayerList(tournamentPlayers);
  }

}
