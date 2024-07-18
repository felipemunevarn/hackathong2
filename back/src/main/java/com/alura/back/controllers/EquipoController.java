package com.alura.back.controllers;

import com.alura.back.Dtos.responseDto.EquipoResponseDto;
import com.alura.back.entities.Equipo;
import com.alura.back.entities.User;
import com.alura.back.services.interfaceService.IEquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para gestionar los equipos
 * Developer leonardo vargas
 */
@RestController
@RequestMapping("/equipos")
@RequiredArgsConstructor
public class EquipoController {

    private final IEquipoService equipoService;

    // TO-DO --->sacar el id del token en la petición
    @PostMapping
    public ResponseEntity<EquipoResponseDto> crearEquipos(@RequestBody User user){

        Long userId= 1L;
        return new ResponseEntity<>(equipoService.GererarEquipos(userId) , HttpStatus.CREATED);
    }


}
