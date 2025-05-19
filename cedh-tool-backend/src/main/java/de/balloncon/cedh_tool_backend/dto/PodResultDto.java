package de.balloncon.cedh_tool_backend.dto;

import de.balloncon.cedh_tool_backend.pod.PodType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PodResultDto(
    @NotNull(message = "podId must be provided") UUID podId,
    @NotNull(message = "tournamentId must be provided") UUID tournamentId,
    @NotNull(message = "podType must be provided") PodType podType,
    Result result,
    UUID playerId) {}
