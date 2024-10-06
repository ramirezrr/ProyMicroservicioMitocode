package com.mitocode.microservices.audit_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Document(collection = "Titular")
public class Titular implements Serializable {

    @Serial
    private static final long serialVersionUID = 1236865465L;
    @Id
    private Long id;

    private String numeroDocumento;

    private String nombres;

    private String apellidos;

    private String fechaNacimiento;

    private String direccion;

}
