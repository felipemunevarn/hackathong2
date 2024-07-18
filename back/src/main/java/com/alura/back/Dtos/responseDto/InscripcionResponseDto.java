package com.alura.back.Dtos.responseDto;

import java.time.LocalDate;

public record InscripcionResponseDto(

        Long inscripcionId,
        String nombreInscrito,
        LocalDate fechaInscrion,
        String nombreEvento,
        String equipoAsignado

) {
}
