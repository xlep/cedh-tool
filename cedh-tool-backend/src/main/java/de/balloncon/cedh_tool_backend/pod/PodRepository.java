package de.balloncon.cedh_tool_backend.pod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PodRepository extends JpaRepository<Pod, UUID> {

  @Query("SELECT DISTINCT p FROM Pod p JOIN p.seats s WHERE p.tournament.id = :tournamentId AND p.round IS NOT NULL AND s.player IS NOT NULL")
  List<Pod> findByTournamentId(@Param("tournamentId") UUID tournamentId);

  @Query("SELECT MAX(p.round) FROM Pod p WHERE p.tournament.id = :tournamentId")
  Optional<Integer> findMaxRoundForTournament(@Param("tournamentId") UUID tournamentId);

  @Query("SELECT p FROM Pod p LEFT JOIN FETCH p.seats WHERE p.tournament.id = :tournamentId")
  List<Pod> findAllWithSeats(@Param("tournamentId") UUID tournamentId);

  @Query("SELECT MAX(p.round) FROM Pod p WHERE p.tournament.id = :tournamentId")
  Optional<Integer> findHighestColumnValueForRound(@Param("tournamentId") UUID tournamentId);
}

