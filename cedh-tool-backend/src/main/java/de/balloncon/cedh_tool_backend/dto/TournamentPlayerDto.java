package de.balloncon.cedh_tool_backend.dto;

import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record TournamentPlayerDto(
    UUID tournamentId,
    BigDecimal score,
    TournamentPlayerStatus status,
    PlayerDto player
    ) {}

