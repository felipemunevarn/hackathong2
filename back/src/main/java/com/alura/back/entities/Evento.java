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
    private Long evento_Id;

    @Column( nullable = false)
    private String nombre;

    @Column( nullable = false)
    private LocalDate fechaInscripcion;

    @Column(nullable = false)
    private String estado;

    @OneToMany(mappedBy = "evento")
    private List<Inscripcion> participantes;
}
