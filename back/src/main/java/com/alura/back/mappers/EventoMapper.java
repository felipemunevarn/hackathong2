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

    public Evento toEvento(EventoRequestDto eventoRequestDto) {
        Evento evento = new Evento();
        evento.setNombre(eventoRequestDto.getNombre());
        evento.setMaxIntegrantesPorEquipo(eventoRequestDto.getMaxIntegrantesPorEquipo());
        evento.setMinIntegrantesPorEquipo(eventoRequestDto.getMinIntegrantesPorEquipo());
        // No need to set fechaCreacion and estado here, as they are set in the save method
        return evento;
    }

    public List<EventoResponseDto> toEventoResponseDtos(List<Evento> eventos) {
        return eventos.stream().map(this::toEventoResponseDto).collect(Collectors.toList());
    }
}