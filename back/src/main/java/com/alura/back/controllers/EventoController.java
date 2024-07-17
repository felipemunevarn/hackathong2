package com.alura.back.controllers;

import com.alura.back.entities.Evento;
import com.alura.back.services.interfaceService.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eventos")
public class EventoController {

    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public List<Evento>getAllEventos() {
        return eventoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        return eventoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
    @PostMapping
    public Evento createEvento(@RequestBody Evento evento) {
        return eventoService.save(evento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento evento) {
        return eventoService.findById(id)
                .map(existingEvento -> {
                    evento.setEvento_Id(id);
                    Evento updatedEvento = eventoService.save(evento);
                    return ResponseEntity.ok(updatedEvento);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        if (eventoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        eventoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
