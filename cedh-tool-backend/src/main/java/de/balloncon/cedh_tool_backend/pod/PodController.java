package de.balloncon.cedh_tool_backend.pod;

import de.balloncon.cedh_tool_backend.dto.PodDto;
import de.balloncon.cedh_tool_backend.dto.PodResultDto;
import de.balloncon.cedh_tool_backend.mapper.PodMapper;
import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${apiVersion}/pods")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Pod Controller", description = "Operations related to pod management.")
public class PodController {

  private final PodService podService;
  private final PodMapper podMapper;

  PodController(PodService podService, PodMapper podMapper) {
    this.podService = podService;
    this.podMapper = podMapper;
  }

  @GetMapping("/{podId}")
  @Operation(summary = "Get pod's.", description = "Get the pod with podId.")
  public ResponseEntity<PodDto> getPodById(@PathVariable UUID podId) {
    Pod pod = podService.getPodById(podId);
    return ResponseEntity.ok(podMapper.toDto(pod));
  }

  @GetMapping("/tournament/{tournamentId}")
  @Operation(summary = "Get pod's for a tournament.", description = "Get the pod's for tournament with tournamentId.")
  public ResponseEntity<List<PodDto>> getPodsByTournament(@PathVariable UUID tournamentId) {
    List<Pod> pods = podService.getPodsByTournamentId(tournamentId);
    return ResponseEntity.ok(podMapper.toDto(pods));
  }

  @PutMapping("/{podId}/results")
  @Operation(summary = "Report pod results.", description = "Report the result for pod with podId.")
  public ResponseEntity<Void> reportPodResult(
      @PathVariable UUID podId,
      @RequestBody PodResultDto podResultDto
  ) {
    return podService.reportResult(
        podId,
        podResultDto.playerId(),
        podResultDto.result()
    );
  }
}

