package com.mitocode.licencias.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "TipoLicencia", schema = "MITOCODE")
public class TipoLicencia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TipoLicencia_seq")
    @SequenceGenerator(name = "TipoLicencia_seq", sequenceName = "TipoLicencia_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "El campo solo puede contener letras, números y guiones.")
    private String categoria;

    @NotNull(message = "Las restricciones del tipo de licencia es obligatorio.")
    private String restricciones;

}
