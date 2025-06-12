package de.balloncon.cedh_tool_backend.seat;

import de.balloncon.cedh_tool_backend.player.Player;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeatRepository extends JpaRepository<Seat, SeatId> {

  @Query("SELECT s FROM Seat s JOIN FETCH s.player WHERE s.pod.id = :podId")
  List<Seat> findByPodId(UUID podId);

  @Query("SELECT s FROM Seat s JOIN FETCH s.player WHERE s.pod.id = :podId AND s.player.id = :playerId")
  Seat findByPodAndPlayer(UUID podId, UUID playerId);

  @Query("SELECT s FROM Seat s JOIN s.pod p WHERE s.player.id = :playerId AND p.tournament.id = :tournamentId")
  List<Seat> findByTournamentAndPlayer(UUID tournamentId, UUID playerId);

  @Query(value = """
        SELECT DISTINCT p.*
        FROM player p
        JOIN seats s ON p.id = s.player
        JOIN pod pd ON s.pod = pd.id
        WHERE pd.tournament_id = :tournamentId
        """, nativeQuery = true)
  List<Player> findDistinctPlayersByTournamentId(@Param("tournamentId") UUID tournamentId);
}
