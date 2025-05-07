package de.balloncon.cedh_tool_backend.dto;

import java.util.List;
import java.util.UUID;

public record PodDto(
        UUID podId,
        int podNumber,
        List<PlayerDto> players
) {}

