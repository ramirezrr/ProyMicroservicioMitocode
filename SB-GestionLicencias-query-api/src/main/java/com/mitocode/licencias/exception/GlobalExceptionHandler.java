package com.mitocode.licencias.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    private static Map<String, String> geErrors(DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        Throwable rootCause = ex.getMostSpecificCause();
        String errorMessage = rootCause.getMessage();

        if (errorMessage.contains("FKU4KNHC4XW0ERXUF0SK197OJE")) {
            errors.put("titular", "Error con la clave foránea de Titular.");
        } else if (errorMessage.contains("FK9YYDAI3CLV3O7T2MNTSYC76LJ")) {
            errors.put("tipoLicencia", "Error con la clave foránea de TipoLicencia");
        } else {
            errors.put("Violación de integridad de datos", errorMessage);
        }
        return errors;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorGenericResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        log.info("ERROR GENERADO: {} {}", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), ex);
        ErrorGenericResponse errorGenericResponse = new ErrorGenericResponse();
        errorGenericResponse.setMessage("Ocurrieron errores en los parámetros de entrada.");
        errorGenericResponse.setStatus(ex.getStatusCode().toString());

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        errorGenericResponse.setErrors(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorGenericResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorGenericResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        log.info("ERROR GENERADO: {} {}", ex.getMessage(), ex);
        ErrorGenericResponse errorGenericResponse = new ErrorGenericResponse();
        errorGenericResponse.setMessage("Ocurrieron errores en los parámetros de entrada.");
        errorGenericResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
        Map<String, String> errors = geErrors(ex);
        errorGenericResponse.setErrors(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorGenericResponse);
    }

    @ExceptionHandler(ErrorGenericException.class)
    public ResponseEntity<ErrorGenericResponse> handleIllegalArgumentException(ErrorGenericException e) {
        log.info("ERROR GENERADO: {} {}", e.getMessage(), e);

        ErrorGenericResponse errorResponse = new ErrorGenericResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(e.getCodigoError());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(FunctionalGenericResponse.class)
    public ResponseEntity<ErrorGenericResponse> functionalGenericResponse(FunctionalGenericResponse e) {
        log.info("ERROR GENERADO: {} {}", e.getMensajeRespuesta(), e);

        ErrorGenericResponse errorResponse = new ErrorGenericResponse();
        errorResponse.setMessage(e.getMensajeRespuesta());
        errorResponse.setStatus(e.getCodigoRespuesta());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorGenericResponse> handleException(Exception ex) {
        log.info("ERROR GENERADO: {} {}", ex.getMessage(), ex);
        ErrorGenericResponse errorResponse = new ErrorGenericResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setDetails(ex.getStackTrace().toString());
        errorResponse.setDate(LocalDate.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
