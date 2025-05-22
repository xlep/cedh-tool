package de.balloncon.cedh_tool_backend.tournament;

import de.balloncon.cedh_tool_backend.dto.RoundDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("${apiVersion}/tournaments")
public class TournamentController {

  private final TournamentService tournamentService;
  private final TournamentRepository tournamentRepository;

  TournamentController(TournamentService tournamentService, TournamentRepository tournamentRepository) {
    this.tournamentService = tournamentService;
    this.tournamentRepository = tournamentRepository;
  }

  @PostMapping("{tournamentId}/rounds")
  public ResponseEntity<RoundDto> createNewRound(@PathVariable UUID tournamentId) {
    RoundDto newRound = tournamentService.generateNextRound(tournamentId);
    return ResponseEntity.ok(newRound);
  }

  @PostMapping("/topcut/{tournamentId}/{cutTo}")
  public ResponseEntity createTopCut(
      @PathVariable("tournamentId") UUID tournamentId, @PathVariable("cutTo") int cutTo) {
    return tournamentService.determineCut(tournamentId, cutTo);
  }

  @GetMapping("{tournamentId}/rounds")
  public ResponseEntity<List<RoundDto>> getRounds(@PathVariable UUID tournamentId) {
    List<RoundDto> rounds = tournamentService.getRoundsByTournamentId(tournamentId);
    return ResponseEntity.ok(rounds);
  }
}
