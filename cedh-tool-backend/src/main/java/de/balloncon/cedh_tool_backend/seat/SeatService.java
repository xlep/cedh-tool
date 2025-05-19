package de.balloncon.cedh_tool_backend.seat;

import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SeatService {

  @Autowired SeatRepository seatRepository;

  @Transactional
  public void saveResult(UUID podId, UUID playerId, Result result) {
    Seat seat = seatRepository.findByPodAndPlayer(podId, playerId);

    if (seat != null) {
      seat.setResult(result.toString());
      seatRepository.save(seat);
    }
  }
}
