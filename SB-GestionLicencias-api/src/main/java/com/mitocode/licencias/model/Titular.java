package com.mitocode.licencias.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Titular", schema = "MITOCODE")
public class Titular {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Titular_seq")
    @SequenceGenerator(name = "Titular_seq", sequenceName = "Titular_seq", allocationSize = 1)
    @Schema(description = "ID único del titular", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "El número Documento del titular es obligatorio.")
    @Min(value = 10000000, message = "El campo dniEstudiante solo acepta valores de 8 dígitos.")
    @Max(value = 99999999, message = "El campo dniEstudiante solo acepta valores de 8 dígitos.")
    @Schema(description = "Número de documento del titular", example = "12345678", required = true)
    private String numeroDocumento;

    @NotNull(message = "Los nombres del titular son obligatorios.")
    @Size(min = 2, message = "El campo nombres acepta mínimo 2 letras.")
    @Pattern(regexp = "^[A-Za-zñÑáéíóúÁÉÍÓÚ]{2,}( [A-Za-zñÑáéíóúÁÉÍÓÚ]{2,})*$", message = "El campo apellido solo acepta letras.")
    @Schema(description = "Nombres del titular", example = "Juan", required = true)
    private String nombres;

    @Size(min = 2, message = "El campo apellidos acepta mínimo 2 letras.")
    @Pattern(regexp = "^[A-Za-zñÑáéíóúÁÉÍÓÚ]{2,}( [A-Za-zñÑáéíóúÁÉÍÓÚ]{2,})*$", message = "El campo apellidos solo acepta letras.")
    @NotNull(message = "El apellido del titular es obligatorio.")
    @Schema(description = "Apellidos del titular", example = "Pérez López", required = true)
    private String apellidos;

    @NotNull(message = "La fecha de nacimiento del titular es obligatoria.")
    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Fecha de nacimiento del titular", example = "1985-06-25", required = true, type = "string", format = "date")
    private LocalDate fechaNacimiento;

    @NotNull(message = "La dirección del titular es obligatoria.")
    @Schema(description = "Dirección del titular", example = "Calle Falsa 123", required = true)
    private String direccion;

}
