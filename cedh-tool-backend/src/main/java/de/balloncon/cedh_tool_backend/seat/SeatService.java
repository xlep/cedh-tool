package de.balloncon.cedh_tool_backend.seat;

import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    TournamentPlayerService tournamentPlayerService;

    private final static String KEYWORD_WIN = "win";
    private final static String KEYWORD_DRAW = "draw";
    private final static String KEYWORD_LOSS =  "loss";
    private final static String RESPONSE_OF_WIN = "The win was successfully registered";
    private final static String RESPONSE_OF_DRAW = "The draw was successfully registered";
    private final static String RESPONSE_INTERNAL_SERVER_ERROR = "Wrong keyword for result transmited";

    @Transactional
    public ResponseEntity<String> reportResult (UUID tournamentId, UUID podId, UUID winningPlayerID, String result) {
        return switch (result) {
            case KEYWORD_WIN -> setSeatResults(tournamentId, podId, winningPlayerID);
            case KEYWORD_DRAW -> setSeatResults(tournamentId, podId, null);
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RESPONSE_INTERNAL_SERVER_ERROR);
        };

    }

    private ResponseEntity<String> setSeatResults(UUID tournamentId, UUID podId, UUID winningPlayerID) {
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
        tournamentPlayerService.calculateAndAssignNewScores(tournamentId, playerSeatMap, winningPlayerID);
        return ResponseEntity.status(HttpStatus.OK).body(RESPONSE_OF_WIN);
        }
        tournamentPlayerService.calculateAndAssignNewScores(tournamentId, playerSeatMap, null);
        return ResponseEntity.status(HttpStatus.OK).body(RESPONSE_OF_DRAW);
    }
}
