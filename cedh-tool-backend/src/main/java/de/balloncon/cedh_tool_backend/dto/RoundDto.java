package de.balloncon.cedh_tool_backend.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record RoundDto(
        int round,
        List<PodDto> pods
) {
    @Builder
    public record PodDto(
            UUID podId,
            int podName,
            List<SeatDto> seats
    ) {}
    @Builder
    public record SeatDto(
            UUID playerId,
            String nickname,
            int seat
    ) {}
}

