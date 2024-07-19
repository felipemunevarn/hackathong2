package com.alura.back.controllers;

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
@RequestMapping("/incripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final IInscripcionService inscripcionService;

    //To-do sacar el id de la peticion
    @PostMapping("/{eventoId}")
    public ResponseEntity<InscripcionResponseDto> crearInscripcio(@PathVariable("eventoId") Long eventoId )  {
       Long userID = 1L;
       return new ResponseEntity<InscripcionResponseDto>(inscripcionService.crearInscripcion(eventoId , userID) , HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<InscripcionResponseDto>> ObtenerListaInscriptos(){
        return new ResponseEntity<List<InscripcionResponseDto>>(inscripcionService.ListarInscritos() , HttpStatus.OK);
    }
}
