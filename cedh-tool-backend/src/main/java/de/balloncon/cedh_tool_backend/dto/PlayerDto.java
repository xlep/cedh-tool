package de.balloncon.cedh_tool_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record PlayerDto(
    String nickname,
    @NotBlank(message = "firstname can not be empty") String firstname,
    String lastname,
    @Email String email,
    UUID playerId) {

}
