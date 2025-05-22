package de.balloncon.cedh_tool_backend.seat;

import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.pod.PodType;
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

  //  private ResponseEntity<Void> saveResult(UUID podId, UUID winningPlayerID) {
  //    List<Seat> players = seatRepository.findByPodId(podId);
  //    HashMap<String, Integer> playerSeatMap = new HashMap<String, Integer>();
  //    boolean isWinningPlayer = winningPlayerID != null;
  //
  //    for (Seat seat : players) {
  //      if (isWinningPlayer) {
  //        if (winningPlayerID.equals(seat.getPlayer().getId())) {
  //          seat.setResult(Result.win.toString());
  //          playerSeatMap.put(seat.getPlayer().getId().toString(), seat.getSeat());
  //        } else {
  //          // TODO: find a better way
  //          seat.setResult("loss");
  //          playerSeatMap.put(seat.getPlayer().getId().toString(), seat.getSeat());
  //        }
  //      }
  //      seat.setResult(Result.draw.toString());
  //      playerSeatMap.put(seat.getPlayer().getId().toString(), seat.getSeat());
  //    }
  //    if (isWinningPlayer) {
  ////      tournamentPlayerService.calculateAndAssignNewScores(
  ////          tournamentId, playerSeatMap, winningPlayerID);
  //      return new ResponseEntity<>(HttpStatus.OK);
  //    }
  //    //tournamentPlayerService.calculateAndAssignNewScores(tournamentId, playerSeatMap, null);
  //    return new ResponseEntity<>(HttpStatus.OK);
  //  }

}
