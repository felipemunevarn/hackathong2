package com.alura.back.services.interfaceService;

import com.alura.back.Dtos.responseDto.InscripcionResponseDto;

public interface IInscripcionService {

    public InscripcionResponseDto crearInscripcion ( Long eventoId , Long userId)  ;
}
