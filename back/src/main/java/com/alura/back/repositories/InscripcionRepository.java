package com.alura.back.repositories;

import com.alura.back.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    @Query("select i from Inscripcion i where i.evento.id = :eventoId")
    List<Inscripcion> getInscripcionesEvento(@Param("eventoId") Long eventoId);

}
