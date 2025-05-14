package de.balloncon.cedh_tool_backend.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PodResultDto(
    @NotNull(message = "tournamentId must be provided") java.util.UUID tournamentId,
    @NotNull(message = "podId must be provided") java.util.UUID podId,
    java.util.UUID playerId,
    @NotNull(message = "Result must be provided") @Enumerated(EnumType.STRING) Result result) {

  // Enforces that playerId is non-null when result is Win.
  @AssertTrue(message = "playerId must be provided when result is Win")
  @SuppressWarnings("unused")
  public boolean isPlayerIdValidForWin() {
    return result != Result.win || playerId != null;
  }
}
