package com.alura.back.Dtos.responseDto;

import lombok.Data;

import java.time.LocalDate;


public record EventoResponseDto(
        Long evento_Id,
        String nombre,
        LocalDate fechaCreacion,
        Integer maxIntegrantesPorEquipo,
        Integer minIntegrantesPorEquipo,
        String estado
){


}