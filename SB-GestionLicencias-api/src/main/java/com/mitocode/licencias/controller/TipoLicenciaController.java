package com.mitocode.licencias.controller;

import com.mitocode.licencias.exception.ErrorGenericResponse;
import com.mitocode.licencias.model.TipoLicencia;
import com.mitocode.licencias.service.ITipoLicenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tipos-licencia")
@CrossOrigin(maxAge = 3600, origins = "http://localhost")
@Tag(name = "Tipos de Licencia", description = "Operaciones relacionadas con los tipos de licencia")
public class TipoLicenciaController {

    private final ITipoLicenciaService service;

    @Operation(summary = "Obtener todos los tipos de licencia")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de tipos de licencia", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoLicencia.class))), @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content(schema = @Schema(implementation = ErrorGenericResponse.class)))})
    @GetMapping("/getAllTipoLicencias")
    public ResponseEntity<List<TipoLicencia>> getAllTipoLicencias() {
        return ResponseEntity.ok(service.getAllTipoLicencias());
    }

    @Operation(summary = "Obtener un tipo de licencia por ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Tipo de licencia encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoLicencia.class))), @ApiResponse(responseCode = "400", description = "Tipo de licencia no encontrado", content = @Content(schema = @Schema(implementation = ErrorGenericResponse.class)))})
    @GetMapping("/getTipoLicenciaById/{id}")
    public ResponseEntity<TipoLicencia> getTipoLicenciaById(@Parameter(description = "ID del tipo de licencia") @PathVariable Long id) {
        return ResponseEntity.ok(service.getTipoLicenciaById(id));
    }

    @Operation(summary = "Guardar un nuevo tipo de licencia")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Tipo de licencia creado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoLicencia.class))), @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content(schema = @Schema(implementation = ErrorGenericResponse.class)))})
    @PostMapping("/saveTipoLicencia")
    public ResponseEntity<TipoLicencia> saveTipoLicencia(@Parameter(description = "Tipo de licencia a guardar") @RequestBody TipoLicencia tipoLicencia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveTipoLicencia(tipoLicencia));
    }

    @Operation(summary = "Actualizar un tipo de licencia")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Tipo de licencia actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoLicencia.class))), @ApiResponse(responseCode = "400", description = "Tipo de licencia no encontrado", content = @Content(schema = @Schema(implementation = ErrorGenericResponse.class)))})
    @PutMapping("/updateTipoLicencia/{id}")
    public ResponseEntity<TipoLicencia> updateTipoLicencia(@Parameter(description = "ID del tipo de licencia") @PathVariable Long id, @Parameter(description = "Tipo de licencia a actualizar") @RequestBody TipoLicencia tipoLicencia) {
        tipoLicencia.setId(id);
        return ResponseEntity.ok(service.updateTipoLicencia(tipoLicencia));
    }

    @Operation(summary = "Eliminar un tipo de licencia")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Tipo de licencia eliminado"), @ApiResponse(responseCode = "400", description = "Tipo de licencia no encontrado", content = @Content(schema = @Schema(implementation = ErrorGenericResponse.class)))})
    @DeleteMapping("/deleteTipoLicencia/{id}")
    public ResponseEntity<Void> deleteTipoLicencia(@PathVariable Long id) {
        service.deleteTipoLicencia(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Encontrar tipo de licencia por categoría")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Tipo de licencia encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoLicencia.class))), @ApiResponse(responseCode = "400", description = "Categoría no encontrada", content = @Content(schema = @Schema(implementation = ErrorGenericResponse.class)))})
    @GetMapping("/findByCategoria/{categoria}")
    public ResponseEntity<TipoLicencia> findByCategoria(@Parameter(description = "Categoría del tipo de licencia") @PathVariable String categoria) {
        return ResponseEntity.ok(service.findByCategoria(categoria));
    }

    @Operation(summary = "Encontrar tipos de licencia que contengan una categoría")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de tipos de licencia encontrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoLicencia.class))), @ApiResponse(responseCode = "400", description = "No se encontraron tipos de licencia con la categoría proporcionada", content = @Content(schema = @Schema(implementation = ErrorGenericResponse.class)))})
    @GetMapping("/findByCategoriaContaining")
    public ResponseEntity<List<TipoLicencia>> findByCategoriaContaining(@Parameter(description = "Categoría a buscar en los tipos de licencia") @RequestParam String categoria) {
        return ResponseEntity.ok(service.findByCategoriaContaining(categoria));
    }
}