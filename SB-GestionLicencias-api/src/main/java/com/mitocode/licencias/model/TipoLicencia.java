package com.mitocode.licencias.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "TipoLicencia", schema = "MITOCODE")
public class TipoLicencia {
//        implements Serializable {

//    @Serial
//    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TipoLicencia_seq")
    @SequenceGenerator(name = "TipoLicencia_seq", sequenceName = "TipoLicencia_seq", allocationSize = 1)
    @Schema(description = "ID único del tipo de licencia", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "El campo solo puede contener letras, números y guiones.")
    @Schema(description = "Categoría de la licencia (solo letras, números y guiones)", example = "A1", required = true)
    private String categoria;

    @NotNull(message = "Las restricciones del tipo de licencia son obligatorias.")
    @Schema(description = "Restricciones aplicadas al tipo de licencia", example = "Solo para vehículos livianos", required = true)
    private String restricciones;

}
