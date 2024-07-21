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
@RequestMapping("/eventos")
public class EventoController {
    private final EventoServiceImpl eventoService;

    @Autowired
    public EventoController(EventoServiceImpl eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<?> createEvento(@Valid @RequestBody EventoRequestDto eventoRequestDto) {
        try {
            EventoResponseDto newEvento = eventoService.save(eventoRequestDto);
            return new ResponseEntity<>(newEvento, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception message
            System.out.println("Error while creating event: " + e.getMessage());
            return new ResponseEntity<>(new ErrorResponseDto(e.getMessage()),  HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDto>> getEventos() {
        try {
            List<EventoResponseDto> eventos = eventoService.findAll();
            if (eventos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(eventos, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception message
            System.out.println("Error while fetching events: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponseDto> getEventoById(@PathVariable Long id) {
        EventoResponseDto evento = eventoService.findById(id);
        return evento != null ? new ResponseEntity<>(evento, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvento(@PathVariable Long id, @Valid @RequestBody EventoRequestDto eventoRequestDto) {
        try {
            if (eventoService.findById(id) == null) {
                return new ResponseEntity<>(new ErrorResponseDto("Evento not found with id: " + id), HttpStatus.NOT_FOUND);
            }
            EventoResponseDto updatedEvento = eventoService.update(id, eventoRequestDto);
            return new ResponseEntity<>(updatedEvento, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception message
            System.out.println("Error while updating event: " + e.getMessage());
            return new ResponseEntity<>(new ErrorResponseDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvento(@PathVariable Long id) {
        try {
            if (eventoService.findById(id) == null) {
                return new ResponseEntity<>(new ErrorResponseDto("Evento not found with id: " + id), HttpStatus.NOT_FOUND);
            }
            eventoService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // Log the exception message
            System.out.println("Error while deleting event: " + e.getMessage());
            return new ResponseEntity<>(new ErrorResponseDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}