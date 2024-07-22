package com.alura.back.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "inscripciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inscripcion_id;

    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDate fechaInscripcion;

    @Column(nullable = false)
    private String estado;

    @ManyToOne()
    @JoinColumn(name = "user_id",  nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    @ManyToOne()
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

}
