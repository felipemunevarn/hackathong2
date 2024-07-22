package com.alura.back.Dtos.requestDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoRequestDto {
    private String nombre;
    private Integer maxIntegrantesPorEquipo;
    private Integer minIntegrantesPorEquipo;
}