package com.alura.back.Dtos.responseDto;

import java.time.LocalDate;

public record UserResponseDto (
    Long user_Id,
    String firstName,
    String lastName,
    LocalDate dateOfBirths,
    String gender,
    String email,
    String password,
    String devType,
    String role
) {
}
