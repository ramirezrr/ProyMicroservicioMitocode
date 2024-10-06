package com.mitocode.licencias.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mitocode.licencias.util.CustomDateDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Licencia", schema = "MITOCODE")
public class Licencia implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Licencia_seq")
    @SequenceGenerator(name = "Licencia_seq", sequenceName = "Licencia_seq", allocationSize = 1)
    @Schema(description = "ID único de la licencia", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Número de la licencia", example = "L00000001")
    private String numLicencia;

    @NotNull(message = "La fecha de emisión de la licencia es obligatoria.")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Fecha de emisión de la licencia", example = "2024-09-24", required = true, type = "string", format = "date")
    private LocalDate fechaEmision;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Fecha de vencimiento de la licencia", example = "2024-08-24", type = "string", format = "date")
    private LocalDate fechaVencimiento;

    @NotNull(message = "El estado de la licencia es obligatorio.")
    @Pattern(regexp = "ACTIVO|INACTIVO", message = "El estado de la licencia debe ser ACTIVO o INACTIVO")
    @Schema(description = "Estado de la licencia (ACTIVO o INACTIVO)", example = "ACTIVO", required = true)
    private String estado;

    @NotNull(message = "El titular es obligatorio.")
    @ManyToOne
    @JoinColumn(name = "titular_id", nullable = false)
    @Schema(description = "Titular de la licencia", required = true)
    @Valid
    private Titular titular;

    @NotNull(message = "El tipo de licencia es obligatorio.")
    @ManyToOne
    @JoinColumn(name = "tipoLicencia_id", nullable = false)
    @Schema(description = "Tipo de licencia", required = true)
    @Valid
    private TipoLicencia tipoLicencia;

    @Schema(description = "Indica si la licencia ha sido eliminada lógicamente", example = "false", defaultValue = "false")
    private boolean deleted = false; // Para eliminación lógica

}
