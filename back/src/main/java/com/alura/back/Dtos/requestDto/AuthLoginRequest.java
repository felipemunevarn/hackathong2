package com.alura.back.Dtos.requestDto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(@NotBlank String userEmail,
                               @NotBlank String password) {
}
