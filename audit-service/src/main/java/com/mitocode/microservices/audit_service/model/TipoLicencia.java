package com.mitocode.microservices.audit_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@Document(collection = "TipoLicencia")
public class TipoLicencia implements Serializable {

    @Serial
    private static final long serialVersionUID = 1236865465L;

    @Id
    private Long id;

    private String categoria;

    private String restricciones;

}
