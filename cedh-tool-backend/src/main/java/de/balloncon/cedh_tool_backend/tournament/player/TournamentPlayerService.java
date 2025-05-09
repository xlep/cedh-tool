package de.balloncon.cedh_tool_backend.tournament.player;

import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.tournament.player.score.view.TournamentPlayerScoreView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TournamentPlayerService {

    @Autowired
    private final TournamentPlayerRepository repository;

    public TournamentPlayerService(TournamentPlayerRepository repository) {
        this.repository = repository;
    }

    public List<TournamentPlayerScoreView> getPlayerScoresByTournament(UUID tournamentId) {
        return repository.findPlayerScoresByTournament(tournamentId);
    }

    public List<Player> getAllPlayersByTournament (UUID tournamentId) {
        return repository.findPlayersByTournamentId(tournamentId);
    }
}
