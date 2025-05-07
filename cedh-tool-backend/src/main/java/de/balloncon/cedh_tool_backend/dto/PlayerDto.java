package de.balloncon.cedh_tool_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PlayerDto(
        @NotBlank (message = "nickname can not be empty") String nickname,
        @NotBlank (message = "firstname can not be empty") String firstname,
        @NotBlank (message = "lastname can nit be empty") String lastname,
        @Email String email) {}

