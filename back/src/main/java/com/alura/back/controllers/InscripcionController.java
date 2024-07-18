package com.alura.back.controllers;

import com.alura.back.Dtos.DtoTest;
import com.alura.back.Dtos.responseDto.InscripcionResponseDto;
import com.alura.back.services.interfaceService.IInscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final IInscripcionService inscripcionService;

    @GetMapping()
    public String test () {
        return "texto recibido";
    }


    @PostMapping()
    public ResponseEntity<DtoTest> crearInscripcion() {

        DtoTest dtoTest = new DtoTest();
        return  ResponseEntity.ok(dtoTest);

    }


    //sacar el id de la peticion
    @PostMapping("/{eventoId}")
    public ResponseEntity<InscripcionResponseDto> crearInscripcio(@PathVariable("eventoId") Long eventoId )  {
       Long userID = 1L;
       return new ResponseEntity<InscripcionResponseDto>(inscripcionService.crearInscripcion(eventoId , userID) , HttpStatus.CREATED);

    }





}
