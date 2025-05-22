package de.balloncon.cedh_tool_backend.tournament.player;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.tournament.player.score.view.TournamentPlayerScoreView;
import de.balloncon.cedh_tool_backend.tournament.player.score.view.TournamentPlayerScoreViewFactory;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    return tournamentPlayerService.getPlayerScoresByTournamentId(tournamentId);
  }

  @GetMapping("/score/{round}")
  public List<TournamentPlayerScoreView> getPlayerScoresByTournamentRound(
      @PathVariable int round,
      @RequestParam UUID tournamentId) {
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService
        .calculatePlayerScoresAfterSwissRounds(tournamentId, round);
    return tournamentPlayerScoreViewFactory.createFromTournamentPlayerList(tournamentPlayers);
  }

  @GetMapping("/{tournamentId}")
  public List<PlayerDto> getPlayersByTournamentId(@PathVariable UUID tournamentId) {
    return tournamentPlayerService.getPlayersById(tournamentId);
  }
}