package de.balloncon.cedh_tool_backend.player;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {

  @Query("SELECT tp.player FROM TournamentPlayer tp WHERE tp.tournament.id = :tournamentId")
  List<Player> findByTournamentId(@Param("tournamentId") UUID tournamentId);

}
