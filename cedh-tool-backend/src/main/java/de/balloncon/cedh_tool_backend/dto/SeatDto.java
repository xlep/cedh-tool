package de.balloncon.cedh_tool_backend.dto;

import java.util.UUID;

public record SeatDto(
    UUID podId,
    UUID playerId,
    Integer seat,
    Result result
) {}
