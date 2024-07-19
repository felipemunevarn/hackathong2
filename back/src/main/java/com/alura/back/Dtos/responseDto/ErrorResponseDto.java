package com.alura.back.Dtos.responseDto;

import lombok.Data;
import org.springframework.http.HttpStatus;

public record ErrorResponseDto(
        String mensaje,
        HttpStatus codigoError
) {
}
