package de.balloncon.cedh_tool_backend.tournament;

import de.balloncon.cedh_tool_backend.dto.RoundDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/${apiVersion}/tournaments")
public class TournamentController {

  private final TournamentService tournamentService;

  TournamentController(TournamentService tournamentService) {
    this.tournamentService = tournamentService;
  }

  @PostMapping("{tournamentId}/rounds")
  public ResponseEntity<RoundDto> createNewRound(@PathVariable UUID tournamentId) {
    RoundDto newRound = tournamentService.generateNextRound(tournamentId);
    return ResponseEntity.ok(newRound);
  }
 }
