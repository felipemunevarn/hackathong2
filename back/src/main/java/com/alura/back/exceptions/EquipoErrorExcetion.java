package com.alura.back.exceptions;

import org.springframework.http.HttpStatus;

public class EquipoErrorExcetion  extends RuntimeException{
    private final String mensaje;
    private final HttpStatus statusCode;

    public EquipoErrorExcetion(String mensaje, HttpStatus statusCode) {
        super(mensaje);
        this.mensaje = mensaje;
        this.statusCode = statusCode;
    }

}
