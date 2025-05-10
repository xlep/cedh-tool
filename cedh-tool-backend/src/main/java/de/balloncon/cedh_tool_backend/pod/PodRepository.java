package de.balloncon.cedh_tool_backend.pod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PodRepository extends JpaRepository<Pod, UUID> {
    @Query("SELECT p FROM Pod p WHERE p.round IS NOT NULL AND EXISTS (SELECT 1 FROM Seat s WHERE s.pod = p AND s.player IS NOT NULL AND s.pod.id IN (SELECT p2.id FROM Pod p2 WHERE p2.round IS NOT NULL))")
    List<Pod> findByTournamentId(@Param("tournamentId") UUID tournamentId);

    @Query("SELECT MAX(p.round) FROM Pod p")
    Optional<Integer> findMaxRoundForTournament(@Param("tournamentId") UUID tournamentId);

    @Query("SELECT p FROM Pod p LEFT JOIN FETCH p.seats WHERE p.tournament.id = :tournamentId")
    List<Pod> findAllWithSeats(@Param("tournamentId") UUID tournamentId);
}
