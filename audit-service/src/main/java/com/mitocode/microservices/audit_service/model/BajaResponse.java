package com.mitocode.microservices.audit_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BajaResponse {
    private String codigoRespuesta;
    private String mensajeRespuesta;
    private String proceso;
    private Licencia licencia;

}
