package com.alura.back.Dtos.requestDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(@NotBlank String userEmail,
                                    @NotBlank String password,
                                    @Valid AuthCreateRoleRequest roleRequest) {
}
