package com.alura.back.mappers;

import com.alura.back.Dtos.requestDto.EventoRequestDto;
import com.alura.back.Dtos.responseDto.EventoResponseDto;
import com.alura.back.entities.Evento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventoMapper {

    public EventoResponseDto toEventoResponseDto(Evento evento) {
    EventoResponseDto dto = new EventoResponseDto(
            evento.getEvento_id(),
            evento.getNombre(),
            evento.getFechaCreacion(),
            evento.getMaxIntegrantesPorEquipo(),
            evento.getMinIntegrantesPorEquipo(),
            evento.getEstado()
    );
    return dto;
}

    public Evento toEvento(EventoRequestDto dto) {
        Evento evento = new Evento();
        evento.setNombre(dto.getNombre());
        evento.setEstado(dto.getEstado());
        evento.setFechaCreacion(dto.getFechaCreacion());
        evento.setMaxIntegrantesPorEquipo(dto.getMaxIntegrantesPorEquipo());
        evento.setMinIntegrantesPorEquipo(dto.getMinIntegrantesPorEquipo());
        return evento;
    }

    public List<EventoResponseDto> toEventoResponseDtos(List<Evento> eventos) {
        return eventos.stream().map(this::toEventoResponseDto).collect(Collectors.toList());
    }
}