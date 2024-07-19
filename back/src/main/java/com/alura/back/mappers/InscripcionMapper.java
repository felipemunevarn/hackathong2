package com.alura.back.mappers;

import com.alura.back.Dtos.responseDto.InscripcionResponseDto;
import com.alura.back.entities.Equipo;
import com.alura.back.entities.Evento;
import com.alura.back.entities.Inscripcion;
import com.alura.back.entities.User;
import com.alura.back.utils.EstadoInscripcion;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class InscripcionMapper {
    private Inscripcion inscripcion;
    private List<Inscripcion> listInscripcion;


    public InscripcionResponseDto fromInscripcionToInscripcionResponseDto(){
        return new InscripcionResponseDto(inscripcion.getInscripcion_Id(),
                inscripcion.getUser().getFirstName() + " " + inscripcion.getUser().getLastName(),
                inscripcion.getFechaInscripcion(),
                inscripcion.getEvento().getNombre(),
                EstadoInscripcion.ESPERA.name());
    }

    public Inscripcion fromDataToInscripcion (User user, Equipo equipo, Evento evento){
        Inscripcion inscrip = new Inscripcion();

        inscrip.setFechaInscripcion(LocalDate.now());
        inscrip.setEstado(EstadoInscripcion.ESPERA.name());
        inscrip.setUser(user);
        inscrip.setEvento(evento);
        inscrip.setEquipo(equipo);

        this.inscripcion = inscrip;

        return inscripcion;
    }

    public List<InscripcionResponseDto> fromListIncripcionToListINcripcionResponseDto(List<Inscripcion> inscripciones) {
        return inscripciones.stream()
                .map(inscripcion -> new InscripcionResponseDto(
                        inscripcion.getInscripcion_Id(),
                        inscripcion.getEvento().getNombre(),
                        inscripcion.getFechaInscripcion(),
                        inscripcion.getEvento().getNombre(),
                        inscripcion.getEstado()
                ))
                .collect(Collectors.toList());
    }
}
