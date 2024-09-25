package com.mitocode.licencias.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mitocode.licencias.util.CustomDateDeserializer;
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
    private Long id;

    @NotNull(message = "El numero Documento del titular es obligatorio.")
    @Min(value = 10000000, message = "El campo dniEstudiante solo acepta valores de 8 dígitos.")
    @Max(value = 99999999, message = "El campo dniEstudiante solo acepta valores de 8 dígitos.")
    private String numeroDocumento;

    @NotNull(message = "El nombres del titular es obligatorio.")
    @Size(min = 2, message = "El campo nombres acepta mínimo 2 letras.")
    @Pattern(regexp = "^[A-Za-zñÑáéíóúÁÉÍÓÚ]{2,}( [A-Za-zñÑáéíóúÁÉÍÓÚ]{2,})*$", message = "El campo apellido solo acepta letras.")
    private String nombres;

    @Size(min = 2, message = "El campo apellidos acepta mínimo 2 letras.")
    @Pattern(regexp = "^[A-Za-zñÑáéíóúÁÉÍÓÚ]{2,}( [A-Za-zñÑáéíóúÁÉÍÓÚ]{2,})*$", message = "El campo apellidos solo acepta letras.")
    @NotNull(message = "El apellido del titular es obligatorio.")
    private String apellidos;

    @NotNull(message = "La fecha Nacimiento del titular es obligatorio.")
    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate fechaNacimiento;

    @NotNull(message = "La dirección del titular es obligatorio.")
    private String direccion;

}
