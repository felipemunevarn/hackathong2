package com.alura.back.exceptions;

import com.alura.back.Dtos.responseDto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Autor Leonardo vargas
 * Clase que maneja las excepciones de manera global
 */
@RestControllerAdvice
public class HandelerExceptionClass {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleGeneralExceptions(NotFoundException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ex.getMensaje() , ex.getStatusCode());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }


}
