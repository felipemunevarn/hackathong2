package com.alura.back.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "direcciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long direccion_Id;

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String calle;

    @Column(nullable = false)
    private String codigoPostal;
}
