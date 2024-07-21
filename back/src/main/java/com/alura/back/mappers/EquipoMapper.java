package com.alura.back.mappers;

import com.alura.back.Dtos.responseDto.EquipoResponseDto;
import com.alura.back.Dtos.responseDto.InscripcionResponseDto;
import com.alura.back.entities.Equipo;

import java.util.List;
import java.util.stream.Collectors;

public class EquipoMapper {

    public static List<EquipoResponseDto> fromlistEquipoEventoToEquipoResponseDto(List<Equipo> equiposEvento){
     return   equiposEvento.stream()
                .map(equipo -> new EquipoResponseDto(
                        equipo.getEquipo_id(),
                        equipo.getNombre(),
                        equipo.getEstado(),
                        equipo.getParticipantes().stream()
                                .map(participante -> new InscripcionResponseDto(
                                        participante.getInscripcion_id(),
                                        participante.getUser().getFirstName() +  participante.getUser().getLastName(),
                                        participante.getUser().getDevType(),
                                        participante.getFechaInscripcion(),
                                        participante.getEvento().getNombre(),
                                        participante.getEstado()

                                ))
                                .collect(Collectors.toList())
                )).collect(Collectors.toList());

    }

}
