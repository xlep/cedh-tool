package de.balloncon.cedh_tool_backend.seat;

import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.pod.Pod;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeatService {

  @Autowired
  SeatRepository seatRepository;

  @Transactional
  public void saveResult(UUID podId, UUID playerId, Result result) {
    Seat seat = seatRepository.findByPodAndPlayer(podId, playerId);

    if (seat != null) {
      seat.setResult(result);
      seatRepository.save(seat);
    }
  }

  public void generateAndPersistSeat(Pod pod, Player player, int seatNum) {
    Seat seat = new Seat();
    seat.setPod(pod);
    seat.setPlayer(player);
    seat.setSeat(seatNum);
    seatRepository.save(seat);
  }

  public List<Seat> getPlayerSeatsByTournament(UUID tournamentId, UUID playerId) {
    return seatRepository.findByTournamentAndPlayer(tournamentId, playerId);
  }

  public List<Player> getPlayerByTournament(UUID tournamentId) {
    return seatRepository.findDistinctPlayersByTournamentId(tournamentId);
  }

  // used by tests
  public List<Seat> getSeatsByPodId(UUID podId) {
    return seatRepository.findByPodId(podId);
  }
}
