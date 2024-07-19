package com.alura.back.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long user_Id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirths;
    private String gender;
    private String email;
    private String devType;
    private String role;

    @OneToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;
}
