package com.alura.back.controllers;

import com.alura.back.Dtos.requestDto.EventoRequestDto;
import com.alura.back.Dtos.responseDto.ErrorResponseDto;
import com.alura.back.Dtos.responseDto.EventoResponseDto;
import com.alura.back.services.implementService.EventoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/eventos")
public class EventoController {
    private final EventoServiceImpl eventoService;

    @Autowired
    public EventoController(EventoServiceImpl eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<?> createEvento(@Valid @RequestBody EventoRequestDto eventoRequestDto) {
        return new ResponseEntity<>(eventoService.save(eventoRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDto>> getEventos() {
        return new ResponseEntity<>(eventoService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventoResponseDto> getEventoById(@PathVariable Long id) {
        EventoResponseDto evento = eventoService.findById(id);
        return evento != null ? new ResponseEntity<>(evento, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEvento(@PathVariable Long id, @Valid @RequestBody EventoRequestDto eventoRequestDto) {
        EventoResponseDto updateEvento = eventoService.update(id, eventoRequestDto);
        return updateEvento != null ? new ResponseEntity<>(updateEvento, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEvento(@PathVariable Long id) {
        eventoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}