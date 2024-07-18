package com.alura.back.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 *
 * @author leonardo varags
 */

@Data
public class MiException extends RuntimeException {

    private final String mensaje;
    private final HttpStatus statusCode;

    public MiException(String mensaje, HttpStatus statusCode) {
        super(mensaje);
        this.mensaje = mensaje;
        this.statusCode = statusCode;
    }

    public MiException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
        this.statusCode = null;

    }

}
