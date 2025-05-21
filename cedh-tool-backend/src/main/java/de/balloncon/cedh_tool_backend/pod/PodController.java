package de.balloncon.cedh_tool_backend.pod;

import de.balloncon.cedh_tool_backend.dto.PodDto;
import de.balloncon.cedh_tool_backend.dto.PodResultDto;
import de.balloncon.cedh_tool_backend.mapper.PodMapper;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${apiVersion}")
public class PodController {

  private final PodService podService;
  private final PodMapper podMapper;

  PodController(PodService podService, PodMapper podMapper) {
    this.podService = podService;
    this.podMapper = podMapper;
  }

  @GetMapping("pod/{podId}")
  public ResponseEntity<PodDto> getPodById(@PathVariable UUID podId) {
    Pod pod = podService.getPodById(podId);
    return ResponseEntity.ok(podMapper.toDto(pod));
  }

  @GetMapping("pods/{tournamentId}")
  ResponseEntity<List<PodDto>> pods(@PathVariable UUID tournamentId) {
    List <Pod> pods = podService.getPodsByTournamentId(tournamentId);
    return ResponseEntity.ok(podMapper.toDto(pods));
  }

  @PostMapping("report/result")
  public ResponseEntity<Void> reportResult(@RequestBody PodResultDto podResultDto) {
    return podService.reportResult(
        podResultDto.podId(),
        podResultDto.playerId(),
        podResultDto.result(),
        podResultDto.tournamentId(),
        podResultDto.podType());
  }
}
