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
  @Autowired TournamentPlayerService tournamentPlayerService;

  @Autowired
  PodService podService;

  @Autowired
  PlayerService playerService;

  private static final String KEYWORD_WIN = "win";
  private static final String KEYWORD_DRAW = "draw";
  private static final String KEYWORD_LOSS = "loss";
  private static final String KEYWORD_SEMIFINALS = "semifinals";
  private static final String KEYWORD_FINALS = "finals";


  @Transactional
  public void saveResult(UUID podId, UUID playerId, Result result) {
    Seat seat = seatRepository.findByPodAndPlayer(podId, playerId);

    if (seat != null) {
      seat.setResult(result.toString());
      seatRepository.save(seat);
    }

    reportSemifinalAndFinal(tournamentId, winningPlayerID, result);

    tournamentPlayerService.calculateAndAssignNewScores(tournamentId, playerSeatMap, null);
    return ResponseEntity.status(HttpStatus.OK).body(ResponseMessages.RESPONSE_OF_DRAW);
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
private void reportSemifinalAndFinal(UUID tournamentId, UUID winningPlayerID, String result) {
    if (result.contains(KEYWORD_SEMIFINALS)) {
        Pod finalPod = podService.getFinalPodByTournamentIdAndType(tournamentId, PodType.valueOf(result));
        Player winningPlayer = playerService.findPlayerById(winningPlayerID);
        Set<Seat> seats = finalPod.getSeats();
        Seat newFinalist = generateSeat(finalPod, winningPlayer, seats.size()+1);
        seats.add(newFinalist);
    } else if (result.contains(KEYWORD_FINALS)) {
        // todo logic for final
    }
}

    public void generateAndPersistSeat(Pod pod, Player player, int seatNum) {
        Seat seat = generateSeat(pod, player, seatNum);
        seatRepository.save(seat);
    }

    Seat generateSeat(Pod pod, Player player, int seatNum) {
        Seat seat = new Seat();
        seat.setPod(pod);
        seat.setPlayer(player);
        seat.setSeat(seatNum);
        return seat;
    }
}
