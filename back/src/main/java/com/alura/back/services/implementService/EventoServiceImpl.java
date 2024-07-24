package com.alura.back.services.implementService;

import com.alura.back.Dtos.requestDto.EventoRequestDto;
import com.alura.back.Dtos.responseDto.EventoResponseDto;
import com.alura.back.entities.Evento;
import com.alura.back.mappers.EventoMapper;
import com.alura.back.repositories.EventoRepository;
import com.alura.back.services.interfaceService.IEventService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventoServiceImpl implements IEventService {

    private final EventoRepository eventoRepository;
    private final EventoMapper eventoMapper;

    @Autowired
    public EventoServiceImpl(EventoRepository eventoRepository, EventoMapper eventoMapper) {
        this.eventoRepository = eventoRepository;
        this.eventoMapper = eventoMapper;
    }

    @Override
    public List<EventoResponseDto> findAll() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventoMapper.toEventoResponseDtos(eventos);
    }

    @Override
    public EventoResponseDto findById(Long id) {
        Evento evento = eventoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Evento not found with id: " + id)); // Throw exception if not found
        return eventoMapper.toEventoResponseDto(evento);
    }

    @Override
    public EventoResponseDto save(EventoRequestDto eventoRequestDto) {
        Evento evento = eventoMapper.toEvento(eventoRequestDto);
        evento.setMaxIntegrantesPorEquipo(eventoRequestDto.getMaxIntegrantesPorEquipo());
        evento.setMinIntegrantesPorEquipo(eventoRequestDto.getMinIntegrantesPorEquipo());

        // Set the state to "ACTIVO" by default
        evento.setEstado("ACTIVO");

        // Set the creation date to the current date
        evento.setFechaCreacion(LocalDate.now());

        evento = eventoRepository.save(evento);
        return eventoMapper.toEventoResponseDto(evento);
    }

    @Override
    public void delete(Long id) {
        eventoRepository.deleteById(id);
    }

    @Override
    public EventoResponseDto update(Long id, EventoRequestDto eventoRequestDto) {
        Evento existingEvento = eventoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Evento not found with id: " + id)); // Throw exception if not found

        existingEvento.setNombre(eventoRequestDto.getNombre());
        existingEvento.setMaxIntegrantesPorEquipo(eventoRequestDto.getMaxIntegrantesPorEquipo());
        existingEvento.setMinIntegrantesPorEquipo(eventoRequestDto.getMinIntegrantesPorEquipo());

        Evento updatedEvento = eventoRepository.save(existingEvento);
        return eventoMapper.toEventoResponseDto(updatedEvento);
    }
}