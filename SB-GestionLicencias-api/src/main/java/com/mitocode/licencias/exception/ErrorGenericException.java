package com.mitocode.licencias.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorGenericException extends RuntimeException {

    private final String codigoError;

    public ErrorGenericException(String codigoError, String mensajeError) {
        super(mensajeError);
        this.codigoError = codigoError;
    }
}
