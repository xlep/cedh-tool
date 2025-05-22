package de.balloncon.cedh_tool_backend.tournament.player;

import de.balloncon.cedh_tool_backend.tournament.player.score.view.TournamentPlayerScoreView;
import de.balloncon.cedh_tool_backend.tournament.player.score.view.TournamentPlayerScoreViewFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("${apiVersion}/tournament/players")
public class TournamentPlayerController {

  private final TournamentPlayerService tournamentPlayerService;
  private final TournamentPlayerScoreViewFactory tournamentPlayerScoreViewFactory;

  TournamentPlayerController(TournamentPlayerService tournamentPlayerService,
                             TournamentPlayerScoreViewFactory scoreViewFactory) {
    this.tournamentPlayerService = tournamentPlayerService;
    this.tournamentPlayerScoreViewFactory = scoreViewFactory;
  }

  // TODO probably not needed anymore
  @GetMapping("/score")
  public List<TournamentPlayerScoreView> getPlayerScoresByTournament(
      @RequestParam UUID tournamentId) {
    return tournamentPlayerService.getPlayerScoresByTournament(tournamentId);
  }

  @GetMapping("/score/{round}")
  public List<TournamentPlayerScoreView> getPlayerScoresByTournamentRound(
          @PathVariable int round,
          @RequestParam UUID tournamentId) {
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService
            .calculatePlayerScoresAfterSwissRounds(tournamentId, round);
    return tournamentPlayerScoreViewFactory.createFromTournamentPlayerList(tournamentPlayers);
  }
}
