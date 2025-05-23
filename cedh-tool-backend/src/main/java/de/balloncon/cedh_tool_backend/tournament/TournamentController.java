package de.balloncon.cedh_tool_backend.tournament;

import de.balloncon.cedh_tool_backend.dto.RoundDto;
import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("${apiVersion}/tournaments")
@Tag(name = "Tournament Controller", description = "Operations related to tournament management.")
public class TournamentController {

  private final TournamentService tournamentService;

  TournamentController(TournamentService tournamentService) {
    this.tournamentService = tournamentService;
  }

  @PostMapping("{tournamentId}/rounds")
  @Operation(summary = "Create new rounds for tournaments.", description = "Create a new round for the tournament with tournamentId.")
  public ResponseEntity<RoundDto> createNewRound(@PathVariable UUID tournamentId) {
    RoundDto newRound = tournamentService.generateNextRound(tournamentId);
    return ResponseEntity.ok(newRound);
  }

  @PostMapping("/topcut/{tournamentId}/{cutTo}")
  @Operation(summary = "Generate the top cut's.", description = "Create the top cut for the tournament with tournamentId and cutTo.")
  public ResponseEntity createTopCut(
      @PathVariable("tournamentId") UUID tournamentId, @PathVariable("cutTo") int cutTo) {
    return tournamentService.determineCut(tournamentId, cutTo);
  }

  @GetMapping("{tournamentId}/rounds")
  @Operation(summary = "Get all rounds for a tournament.", description = "Get all rounds for the tournament with tournamentId.")
  public ResponseEntity<List<RoundDto>> getRounds(@PathVariable UUID tournamentId) {
    List<RoundDto> rounds = tournamentService.getRoundsByTournamentId(tournamentId);
    return ResponseEntity.ok(rounds);
  }
}
