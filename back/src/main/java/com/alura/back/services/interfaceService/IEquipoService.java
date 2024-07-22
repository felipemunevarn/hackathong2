package com.alura.back.services.interfaceService;

import com.alura.back.Dtos.responseDto.EquipoResponseDto;
import com.alura.back.Dtos.responseDto.EquipoResultadoResponseDto;

import java.util.List;

public interface IEquipoService {

    public EquipoResultadoResponseDto GererarEquipos(Long eventoId);


    List<EquipoResponseDto> ListarEquipos(Long eventoId);
}
