package com.alura.back.repositories;

import com.alura.back.entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    @Query("select distinct i.equipo from Inscripcion i where i.evento.id = :eventoId and i.equipo.nombre <> 'DEFAULT'")
    List<Equipo> getListaEquipoevento(@Param("eventoId")  Long eventoId);


}
