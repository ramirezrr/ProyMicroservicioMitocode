package com.mitocode.licencias.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mitocode.licencias.util.CustomDateDeserializer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Licencia", schema = "MITOCODE")
public class Licencia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Licencia_seq")
    @SequenceGenerator(name = "Licencia_seq", sequenceName = "Licencia_seq", allocationSize = 1)
    private Long id;

    //    @NotNull(message = "El numero de licencia es obligatorio.")
    private String numLicencia;

    @NotNull(message = "La fechad de Emisión de la licencia es obligatorio.")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate fechaEmision;

    //    @NotNull(message = "La fecha vencimiento de la licencia es obligatorio.")
    private LocalDate fechaVencimiento;

    @NotNull(message = "El estado de la licencia es obligatorio.")
    @Pattern(regexp = "ACTIVO|INACTIVO", message = "El estado de la licencia debe ser ACTIVO o INACTIVO")
    private String estado;

    @NotNull(message = "El titular es obligatorio.")
    @ManyToOne
    @JoinColumn(name = "titular_id", nullable = false)
    private Titular titular;

    @NotNull(message = "El tipo de licencia es obligatorio.")
    @ManyToOne
    @JoinColumn(name = "tipoLicencia_id", nullable = false)
    private TipoLicencia tipoLicencia;

    //    @NotNull(message = "El campo deleted  de la licencia es obligatorio.")
    private boolean deleted = false; // Para eliminación lógica


}
