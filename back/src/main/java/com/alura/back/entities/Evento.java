package com.alura.back.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "eventos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evento_id;

    @Column( nullable = false)
    private String nombre;

    @Column( nullable = false)
    private LocalDate fechaCreacion;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private int maxIntegrantesPorEquipo;

    @Column(nullable = false)
    private int mimIntegrantesPorEquipo;


    @OneToMany(mappedBy = "evento")
    private List<Inscripcion> participantes;
}
