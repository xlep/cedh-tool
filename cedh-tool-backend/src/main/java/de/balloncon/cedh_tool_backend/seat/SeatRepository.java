package de.balloncon.cedh_tool_backend.seat;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SeatRepository extends JpaRepository<Seat, SeatId> {

  @Query("SELECT s FROM Seat s JOIN FETCH s.player WHERE s.pod.id = :podId")
  List<Seat> findByPodId(UUID podId);

  @Query("SELECT s FROM Seat s JOIN FETCH s.player WHERE s.pod.id = :podId AND s.player.id = :playerId")
  Seat findByPodAndPlayer(UUID podId, UUID playerId);
}
