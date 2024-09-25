package com.mitocode.licencias.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorGenericException extends RuntimeException {

    private final String codigoError;

    public ErrorGenericException(String codigoError, String mensajeError) {
        super(mensajeError);
        this.codigoError = codigoError;
    }
}
