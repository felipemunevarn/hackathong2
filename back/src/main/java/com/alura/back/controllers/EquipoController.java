package com.alura.back.controllers;

import com.alura.back.Dtos.responseDto.EquipoResponseDto;
import com.alura.back.Dtos.responseDto.EquipoResultadoResponseDto;
import com.alura.back.services.interfaceService.IEquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Autor: leonardo vargas
 * email: leonardovargasfp@gmail.com
 * descirpción: clase encargada de la gestion para generar equipos aleatorios y moificarlos
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/equipos")
public class EquipoController {

    private final IEquipoService equipoService;

    // TO-DO --->sacar el id del token en la petición cuando este implementado
    @PostMapping(value = "/{eventoId}")
    public ResponseEntity<EquipoResultadoResponseDto> crearEquipos(@PathVariable("eventoId") Long eventoId){
        return new ResponseEntity<>(equipoService.GererarEquipos(eventoId) , HttpStatus.CREATED);
    }

    @GetMapping(value = "/{eventoId}")
    public ResponseEntity<List<EquipoResponseDto>> ListaInscriptosEvento(@PathVariable("eventoId") Long eventoId) {
        return new ResponseEntity<>(equipoService.ListarEquipos(eventoId), HttpStatus.OK);
    }

}
