package de.balloncon.cedh_tool_backend.tournament;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface TournamentRepository extends JpaRepository<Tournament, UUID> {

    @Query("SELECT t FROM Tournament t WHERE t.id = :id")
    Tournament findTournamentById(@Param("id") UUID id);
}
