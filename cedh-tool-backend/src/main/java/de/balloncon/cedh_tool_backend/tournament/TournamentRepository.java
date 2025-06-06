package de.balloncon.cedh_tool_backend.tournament;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TournamentRepository extends JpaRepository<Tournament, UUID> {

  @Query("SELECT t FROM Tournament t WHERE t.id = :id")
  Tournament findTournamentById(@Param("id") UUID id);

  @Query("SELECT MAX(p.round) FROM Pod p WHERE p.tournament.id = :tournamentId")
  Integer findMaxRound(@Param("tournamentId") UUID tournamentId);
}
