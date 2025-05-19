package de.balloncon.cedh_tool_backend.pod;

import de.balloncon.cedh_tool_backend.dto.PodResultDto;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${apiVersion}")
public class PodController {

  private final PodService podService;

  PodController(PodService podService) {
    this.podService = podService;
  }

  @GetMapping("pods")
  Pod pods(@Parameter UUID podId) {
    return podService.getPodById(podId);
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
