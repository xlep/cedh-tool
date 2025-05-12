package de.balloncon.cedh_tool_backend.seat;

import de.balloncon.cedh_tool_backend.pod.PodRepository;
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
        switch (result) {

            case KEYWORD_WIN:
                // Save win in seat table
                List<Seat> players = seatRepository.findByPodId(podId);
                HashMap<String, Integer> playerSeatMap = new HashMap<String, Integer>();

                for (Seat seat : players) {
                    if (winningPlayerID.equals(seat.getPlayer().getId())) {
                        seat.setResult(KEYWORD_WIN);
                        playerSeatMap.put(seat.getPlayer().getId().toString(), seat.getSeat());
                    } else {
                        seat.setResult(KEYWORD_LOSS);
                       playerSeatMap.put(seat.getPlayer().getId().toString(), seat.getSeat());
                    }
                }

                tournamentPlayerService.calculateNewScoreForPlayers(tournamentId, playerSeatMap, winningPlayerID);

                return ResponseEntity.status(HttpStatus.OK).body(RESPONSE_OF_WIN);

            case KEYWORD_DRAW:
                // handle draw
                return ResponseEntity.status(HttpStatus.OK).body(RESPONSE_OF_DRAW);

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RESPONSE_INTERNAL_SERVER_ERROR);
        }

    }
}
