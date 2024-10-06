package com.mitocode.microservices.audit_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Licencia")
public class Licencia implements Serializable {

    @Serial
    private static final long serialVersionUID = 1236865465L;

    @Id
    private Long id;

    private String numLicencia;

//    @JsonDeserialize(using = CustomDateDeserializer.class)
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private String fechaEmision;

//    @JsonDeserialize(using = CustomDateDeserializer.class)
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private String fechaVencimiento;

    private String estado;

    private Titular titular;

    private TipoLicencia tipoLicencia;

    private boolean deleted = false; // Para eliminación lógica

}
