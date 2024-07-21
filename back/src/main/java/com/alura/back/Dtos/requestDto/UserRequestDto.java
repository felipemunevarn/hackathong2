package com.alura.back.Dtos.requestDto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String password;
    private LocalDate dateOfBirths;
    private String gender;
    private String email;
    private String devType;
    private String role;
}