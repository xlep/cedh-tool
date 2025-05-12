package de.balloncon.cedh_tool_backend.tournament.player;

import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.tournament.player.score.view.TournamentPlayerScoreView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TournamentPlayerRepository extends JpaRepository <TournamentPlayer, UUID> {
    @Query(value = """
        SELECT tp.score as score, 
               p.nickname as nickname, 
               p.firstname as firstname, 
               p.lastname as lastname
        FROM tournamentplayers tp
        JOIN player p ON tp.player = p.id
        WHERE tp.tournament = :tournamentId
        """, nativeQuery = true)
    List<TournamentPlayerScoreView> findPlayerScoresByTournament(@Param("tournamentId") UUID tournamentId);

    @Query("SELECT tp.player FROM TournamentPlayer tp WHERE tp.tournament.id = :tournamentId")
    List<Player> findAllPlayersByTournamentId(@Param("tournamentId") UUID tournamentId);

    @Query("SELECT tp FROM TournamentPlayer tp WHERE tp.tournament.id = :tournamentId AND tp.player.id IN :playerIds")
    List<TournamentPlayer> findByTournamentAndPlayers(
            @Param("tournamentId") UUID tournamentId,
            @Param("playerIds") List<String> playerIds
    );

}
