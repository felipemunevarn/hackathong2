package com.alura.back.Dtos.responseDto;

import java.util.List;

public record EquipoResponseDto(
        Long equipoId,
        String nombreEquipo,
        String estado,
        List<InscripcionResponseDto> participantes) {

}
