package de.balloncon.cedh_tool_backend.seat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, SeatId> {
    List<Seat> findByPodId(UUID podId);
}
