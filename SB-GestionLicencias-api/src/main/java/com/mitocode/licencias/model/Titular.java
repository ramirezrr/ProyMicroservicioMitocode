package com.mitocode.licencias.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Titular", schema = "MITOCODE")
public class Titular {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Titular_seq")
    @SequenceGenerator(name = "Titular_seq", sequenceName = "Titular_seq", allocationSize = 1)
    private Long id;

    @NotNull
    private String numeroDocumento;
    @NotNull
    private String nombres;
    @NotNull
    private String apellidos;
    @NotNull
    private LocalDate fechaNacimiento;
    @NotNull
    private String direccion;

}
