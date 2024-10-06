package com.mitocode.licencias.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FunctionalGenericResponse extends RuntimeException {
    private String codigoRespuesta;
    private String mensajeRespuesta;
}
