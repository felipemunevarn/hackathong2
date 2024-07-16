package com.alura.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {
    private Long user_id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirths;
    private String gender;
    private String email;
    private String devType;
    private String role;
}
