package com.alura.back.controllers;

import com.alura.back.Dtos.responseDto.DeleteResponseDto;
import com.alura.back.Dtos.responseDto.InscripcionResponseDto;
import com.alura.back.services.interfaceService.IInscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Autor leonardo vargas
 * clase que recibe las peticiones relacionadas a las entidad inscripcion
 * con metodos get, post, upadate
 */

@RestController
@RequestMapping("/api/v1/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final IInscripcionService inscripcionService;

    //To-do sacar el id de la petición y validar que no se vuelva a inscribir
    @PostMapping(value = "/{eventoId}/{userId}")
    public ResponseEntity<InscripcionResponseDto> crearInscripcion(@PathVariable("eventoId") Long eventoId , @PathVariable("userId") Long userId) {
    //Long userID = 1L;
       return new ResponseEntity<>(inscripcionService.crearInscripcion(eventoId , userId) , HttpStatus.CREATED);
    }

    @GetMapping("/{eventoId}")
    public ResponseEntity<List<InscripcionResponseDto>> ObtenerListaInscriptos(@PathVariable("eventoId") Long eventoId){
        return new ResponseEntity<>(inscripcionService.ListarInscritos(eventoId) , HttpStatus.OK);
    }

    @DeleteMapping("/{inscripcionId}")
    public ResponseEntity<DeleteResponseDto> darDeBajaInscripcion(@PathVariable("inscripcionId") Long inscripId){
        return new ResponseEntity<>(inscripcionService.darBajaInscripcion(inscripId) , HttpStatus.OK);
    }
}
