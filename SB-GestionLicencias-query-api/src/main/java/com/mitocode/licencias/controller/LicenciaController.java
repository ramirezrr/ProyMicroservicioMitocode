package com.mitocode.licencias.controller;

import com.mitocode.licencias.exception.ErrorGenericResponse;
import com.mitocode.licencias.exception.GenericoResponse;
import com.mitocode.licencias.model.BajaResponse;
import com.mitocode.licencias.model.Licencia;
import com.mitocode.licencias.service.LicenciaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/licencias")
@CrossOrigin(maxAge = 3600, origins = "http://localhost")
@Tag(name = "Licencias", description = "Gestión y emisión de licencias")
public class LicenciaController {

    private final LicenciaServiceImpl licenciaService;


    @Operation(summary = "Listar todas las licencias")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Listado de licencias obtenido exitosamente", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Licencia.class)))}), @ApiResponse(responseCode = "400", description = "Error al obtener las licencias", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenericResponse.class))}), @ApiResponse(responseCode = "500", description = "Error inesperado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenericResponse.class))})})
    @GetMapping("/listLicenses")
    public List<Licencia> listLicenses() {
        log.info("Listando todas las licencias");
        return licenciaService.listarLicencias();
    }

    @Operation(summary = "Listado de licencias eliminadas obtenido exitosamente (estado = INACTIVO & deleted = true)s")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Listado de licencias eliminadas obtenido exitosamente", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Licencia.class)))}), @ApiResponse(responseCode = "400", description = "Error al obtener las licencias eliminadas", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenericResponse.class))}), @ApiResponse(responseCode = "500", description = "Error inesperado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenericResponse.class))})})
    @GetMapping("/listDeletedLicenses")
    public List<Licencia> listDeletedLicenses() {
        return licenciaService.listarLicenciasEliminadas();
    }


    @Operation(summary = "Listar licencia por ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Licencia obtenida exitosamente", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Licencia.class))}), @ApiResponse(responseCode = "400", description = "Licencia no encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenericResponse.class))}), @ApiResponse(responseCode = "500", description = "Error inesperado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenericResponse.class))})})
    @GetMapping("/listLicensesById/{id}")
    public Licencia listLicensesById(@PathVariable Long id) {
        return licenciaService.listarLicenciasPorId(id);
    }


    @Operation(summary = "Listar licencias por estado")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Listado de licencias obtenido exitosamente", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Licencia.class))}), @ApiResponse(responseCode = "400", description = "Licencia no encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenericResponse.class))}), @ApiResponse(responseCode = "500", description = "Error inesperado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorGenericResponse.class))})})
    @GetMapping("/listLicensesByStatus/{estado}")
    public List<Licencia> listLicensesByStatus(@Parameter(description = "Estado de la licencia", example = "ACTIVO", in = ParameterIn.PATH, schema = @Schema(type = "string", allowableValues = {"ACTIVO", "INACTIVO"})) @PathVariable String estado) {
        return licenciaService.listarLicenciasPorEstado(estado);
    }


}
