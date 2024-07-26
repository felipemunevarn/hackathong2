package com.alura.back.Dtos.requestDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AuthCreateUserRequest(
        @NotBlank String userEmail,
        @NotBlank(message = "Password must not be blank or null") String password,
        @Valid AuthCreateRoleRequest roleRequest,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotNull LocalDate dateOfBirths,
        @NotBlank String gender,
        @NotBlank String role
) {
}
