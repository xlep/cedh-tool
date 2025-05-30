package de.balloncon.cedh_tool_backend.dto;

import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record RoundDto(int round, List<PodDto> pods) {

  @Builder
  public record PodDto(UUID podId, int podName, List<SeatDto> seats) {
  }

  @Builder
  public record SeatDto(UUID playerId, String firstname, String lastname, String nickname,
                        int seat, Result result) {
  }
}
