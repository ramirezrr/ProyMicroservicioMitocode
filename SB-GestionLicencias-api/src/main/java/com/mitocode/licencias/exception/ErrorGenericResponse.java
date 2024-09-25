package com.mitocode.licencias.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorGenericResponse {
    private String status;
    private String message;
    private String details;
    private Map<String, String> errors;
    private LocalDate date = LocalDate.now();
}
