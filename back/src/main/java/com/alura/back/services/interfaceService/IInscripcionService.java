package com.alura.back.services.interfaceService;

import com.alura.back.Dtos.responseDto.InscripcionResponseDto;

import java.util.List;

public interface IInscripcionService {

    public InscripcionResponseDto crearInscripcion ( Long eventoId , Long userId)  ;

    List<InscripcionResponseDto> ListarInscritos();
}
