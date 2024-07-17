package com.alura.back.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "equipos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipo_Id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String estado;

    @OneToMany(mappedBy = "equipo")
    private List<Inscripcion> participantes;
}
