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

    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column( nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "max_integrantes_por_equipo", nullable = false)
    private Integer maxIntegrantesPorEquipo;

    @Column(name = "mim_integrantes_por_equipo", nullable = false)
    private Integer minIntegrantesPorEquipo;

    @Column(nullable = false)
    private String estado;
    

    @OneToMany(mappedBy = "evento")
    private List<Inscripcion> participantes;
}
