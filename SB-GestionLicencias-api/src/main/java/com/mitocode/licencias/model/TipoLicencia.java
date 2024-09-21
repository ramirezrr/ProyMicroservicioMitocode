package com.mitocode.licencias.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @NotNull
    private String restricciones;

}
