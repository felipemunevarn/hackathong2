package com.alura.back.services.interfaceService;

import com.alura.back.Dtos.requestDto.EventoRequestDto;
import com.alura.back.Dtos.responseDto.EventoResponseDto;

import java.util.List;

public interface IEventService {
    List<EventoResponseDto> findAll();
    EventoResponseDto findById(Long id);
    EventoResponseDto save(EventoRequestDto eventoRequestDto);
    void delete(Long id);
    EventoResponseDto update(Long id, EventoRequestDto eventoRequestDto); // New update method

}
