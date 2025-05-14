package de.balloncon.cedh_tool_backend.seat;

import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerService;
import de.balloncon.cedh_tool_backend.util.ResponseMessages;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SeatService {

  @Autowired SeatRepository seatRepository;

  @Autowired TournamentPlayerService tournamentPlayerService;

  private static final String KEYWORD_WIN = "win";
  private static final String KEYWORD_DRAW = "draw";
  private static final String KEYWORD_LOSS = "loss";

  @Transactional
  public ResponseEntity<String> reportResult(
      UUID tournamentId, UUID podId, UUID winningPlayerID, String result) {
    return switch (result) {
      case KEYWORD_WIN -> setSeatResults(tournamentId, podId, winningPlayerID);
      case KEYWORD_DRAW -> setSeatResults(tournamentId, podId, null);
      default ->
          ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(ResponseMessages.RESPONSE_BAD_REQUEST_RESULT);
    };
  }

  private ResponseEntity<String> setSeatResults(
      UUID tournamentId, UUID podId, UUID winningPlayerID) {
    List<Seat> players = seatRepository.findByPodId(podId);
    HashMap<String, Integer> playerSeatMap = new HashMap<String, Integer>();
    boolean isWinningPlayer = winningPlayerID != null;

    for (Seat seat : players) {
      if (isWinningPlayer) {
        if (winningPlayerID.equals(seat.getPlayer().getId())) {
          seat.setResult(KEYWORD_WIN);
          playerSeatMap.put(seat.getPlayer().getId().toString(), seat.getSeat());
        } else {
          seat.setResult(KEYWORD_LOSS);
          playerSeatMap.put(seat.getPlayer().getId().toString(), seat.getSeat());
        }
      }
      seat.setResult(KEYWORD_DRAW);
      playerSeatMap.put(seat.getPlayer().getId().toString(), seat.getSeat());
    }
    if (isWinningPlayer) {
      tournamentPlayerService.calculateAndAssignNewScores(
          tournamentId, playerSeatMap, winningPlayerID);
      return ResponseEntity.status(HttpStatus.OK).body(ResponseMessages.RESPONSE_OF_WIN);
    }
    tournamentPlayerService.calculateAndAssignNewScores(tournamentId, playerSeatMap, null);
    return ResponseEntity.status(HttpStatus.OK).body(ResponseMessages.RESPONSE_OF_DRAW);
  }
}
