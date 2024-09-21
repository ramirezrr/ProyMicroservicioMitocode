package com.mitocode.licencias.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private LocalDate fechaEmision;
    @NotNull
    private LocalDate fechaVencimiento;
    @NotNull
    private LocalDate fechaUltimaRenovacion;
    @NotNull
    private LocalDate fechaProximaVencimiento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoLicencia estado;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "titular_id", nullable = false)
    private Titular titular;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tipoLicencia_id", nullable = false)
    private TipoLicencia tipoLicencia;

    @NotNull
    private boolean deleted = false; // Para eliminación lógica

}
