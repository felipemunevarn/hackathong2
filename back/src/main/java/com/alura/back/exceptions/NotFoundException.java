package com.alura.back.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @author leonardo varags
 */

@Data
public class NotFoundException extends RuntimeException {

    private final String mensaje;
    private final HttpStatus statusCode;

    public NotFoundException(String mensaje, HttpStatus statusCode) {
        super(mensaje);
        this.mensaje = mensaje;
        this.statusCode = statusCode;
    }

    public NotFoundException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
        this.statusCode = null;

    }

}
