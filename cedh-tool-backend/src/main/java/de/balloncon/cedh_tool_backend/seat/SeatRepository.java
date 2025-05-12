package de.balloncon.cedh_tool_backend.seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, SeatId> {
    @Query("SELECT s FROM Seat s JOIN FETCH s.player WHERE s.pod.id = :podId")
    List<Seat> findByPodId(UUID podId);
}
