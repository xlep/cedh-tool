package de.balloncon.cedh_tool_backend.seat;

import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.pod.PodType;
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
  public ResponseEntity<String> reportResult(
      UUID tournamentId, UUID podId, UUID winningPlayerID, String result) {
    return switch (result) {
      case KEYWORD_WIN -> setSeatResults(tournamentId, podId, winningPlayerID, null);
      case KEYWORD_DRAW -> setSeatResults(tournamentId, podId, null, null);
      case KEYWORD_SEMIFINALS -> setSeatResults(tournamentId, podId, winningPlayerID, result);
      default ->
          ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(ResponseMessages.RESPONSE_BAD_REQUEST_RESULT);
    };
  }

  private ResponseEntity<String> setSeatResults(
          UUID tournamentId, UUID podId, UUID winningPlayerID, String result) {
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

    reportSemifinalAndFinal(tournamentId, winningPlayerID, result);

    tournamentPlayerService.calculateAndAssignNewScores(tournamentId, playerSeatMap, null);
    return ResponseEntity.status(HttpStatus.OK).body(ResponseMessages.RESPONSE_OF_DRAW);
  }

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
