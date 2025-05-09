package de.balloncon.cedh_tool_backend.tournament.player;

import de.balloncon.cedh_tool_backend.tournament.player.score.view.TournamentPlayerScoreView;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/v1/tournament/players")
public class TournamentPlayerController {

    private final TournamentPlayerService tournamentPlayerService;

    TournamentPlayerController(TournamentPlayerService tournamentPlayerService)
    {
        this.tournamentPlayerService = tournamentPlayerService;
    }

    @GetMapping("/players")
    public List<TournamentPlayerScoreView> getPlayerScoresByTournament(@RequestParam UUID tournamentId) {
        return tournamentPlayerService.getPlayerScoresByTournament(tournamentId);
    }
}
