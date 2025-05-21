package de.balloncon.cedh_tool_backend.pod;

import de.balloncon.cedh_tool_backend.dto.PodDto;
import de.balloncon.cedh_tool_backend.dto.PodResultDto;
import de.balloncon.cedh_tool_backend.mapper.PodMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${apiVersion}/pods")
@CrossOrigin(origins = "http://localhost:3000")
public class PodController {

  private final PodService podService;
  private final PodMapper podMapper;

  PodController(PodService podService, PodMapper podMapper) {
    this.podService = podService;
    this.podMapper = podMapper;
  }

  @GetMapping("/{podId}")
  public ResponseEntity<PodDto> getPodById(@PathVariable UUID podId) {
    Pod pod = podService.getPodById(podId);
    return ResponseEntity.ok(podMapper.toDto(pod));
  }

  @GetMapping("/tournament/{tournamentId}")
  public ResponseEntity<List<PodDto>> getPodsByTournament(@PathVariable UUID tournamentId) {
    List<Pod> pods = podService.getPodsByTournamentId(tournamentId);
    return ResponseEntity.ok(podMapper.toDto(pods));
  }

  @PostMapping("/{podId}/results")
  public ResponseEntity<Void> reportPodResult(
          @PathVariable UUID podId,
          @RequestBody PodResultDto podResultDto
  ) {
    return podService.reportResult(
            podId,
            podResultDto.playerId(),
            podResultDto.result(),
            podResultDto.tournamentId(),
            podResultDto.podType());
  }

  @PatchMapping("/{podId}/result")
  public ResponseEntity<Void> resetPodResult(@PathVariable UUID podId) {
    podService.resetResult(podId);
    return ResponseEntity.noContent().build();
  }}

