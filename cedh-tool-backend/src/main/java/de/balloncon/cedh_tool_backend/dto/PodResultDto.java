package de.balloncon.cedh_tool_backend.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PodResultDto(
    @NotNull(message = "podId must be provided") java.util.UUID podId,
    Result result,
    java.util.UUID playerId) {}