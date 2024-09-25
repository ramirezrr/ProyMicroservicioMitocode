package com.mitocode.licencias.controller;

import com.mitocode.licencias.exception.ErrorGenericResponse;
import com.mitocode.licencias.model.Titular;
import com.mitocode.licencias.service.ITitularService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/titulares")
@CrossOrigin(maxAge = 3600, origins = "http://localhost")
@Tag(name = "Titulares", description = "Operaciones relacionadas con los titulares")
public class TitularController {

    private final ITitularService service;


    @GetMapping("/findByNumeroDocumento/{numeroDocumento}")
    @Operation(summary = "Buscar titular por número de documento", description = "Retorna un titular que coincide con el número de documento proporcionado.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Titular encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Titular.class))), @ApiResponse(responseCode = "400", description = "Número de documento no válido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenericResponse.class))), @ApiResponse(responseCode = "404", description = "Titular no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenericResponse.class)))})
    public ResponseEntity<Titular> findByNumeroDocumento(@PathVariable String numeroDocumento) {
        return ResponseEntity.ok(service.findByNumeroDocumento(numeroDocumento));
    }

    @GetMapping("/getAllTitulares")
    @Operation(summary = "Obtener todos los titulares", description = "Retorna una lista de todos los titulares.")
    @ApiResponse(responseCode = "200", description = "Lista de titulares", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Titular[].class)))
    public ResponseEntity<List<Titular>> getAllTitulares() {
        return ResponseEntity.ok(service.getAllTitulares());
    }

    @GetMapping("/getTitularById/{id}")
    @Operation(summary = "Buscar titular por ID", description = "Retorna un titular que coincide con el ID proporcionado.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Titular encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Titular.class))), @ApiResponse(responseCode = "404", description = "Titular no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenericResponse.class)))})
    public ResponseEntity<Titular> getTitularById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTitularById(id));
    }

    @PostMapping("/saveTitular")
    @Operation(summary = "Guardar nuevo titular", description = "Crea un nuevo titular en el sistema.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Titular creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Titular.class))), @ApiResponse(responseCode = "400", description = "Errores de validación", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenericResponse.class)))})
    public ResponseEntity<Titular> saveTitular(@RequestBody @Valid Titular titular) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveTitular(titular));
    }

    @PutMapping("/updateTitular/{id}")
    @Operation(summary = "Actualizar titular", description = "Actualiza la información de un titular existente.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Titular actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Titular.class))), @ApiResponse(responseCode = "404", description = "Titular no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenericResponse.class))), @ApiResponse(responseCode = "400", description = "Errores de validación", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenericResponse.class)))})
    public ResponseEntity<Titular> updateTitular(@PathVariable Long id, @RequestBody @Valid Titular titular) {
        titular.setId(id);
        return ResponseEntity.ok(service.updateTitular(titular));
    }

    @DeleteMapping("/deleteTitular/{id}")
    @Operation(summary = "Eliminar titular", description = "Elimina un titular del sistema por su ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Titular eliminado exitosamente"), @ApiResponse(responseCode = "404", description = "Titular no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenericResponse.class)))})
    public ResponseEntity<Void> deleteTitular(@PathVariable Long id) {
        service.deleteTitular(id);
        return ResponseEntity.noContent().build();
    }

}