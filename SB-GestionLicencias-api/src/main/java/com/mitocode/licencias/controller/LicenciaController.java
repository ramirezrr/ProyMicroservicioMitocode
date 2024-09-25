package com.mitocode.licencias.controller;

import com.mitocode.licencias.exception.FunctionalGenericResponse;
import com.mitocode.licencias.model.BajaResponse;
import com.mitocode.licencias.model.Licencia;
import com.mitocode.licencias.service.LicenciaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
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


    @Operation(summary = "Validar estado de licencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de la licencia validado exitosamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FunctionalGenericResponse.class))}),
            @ApiResponse(responseCode = "400", description = "No se pudo obtener los datos de la licencia",
                    content = @Content)})
    @PostMapping("/validateLicenseStatus/{numeroLicencia}")
    public ResponseEntity<FunctionalGenericResponse> validateLicenseStatus(@PathVariable String numeroLicencia) {
        FunctionalGenericResponse nuevaLicencia = licenciaService.validarEstadoLicencia(numeroLicencia);
        return ResponseEntity.ok(nuevaLicencia);
    }

    @Operation(summary = "Emitir una nueva licencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Licencia emitida exitosamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Licencia.class))}),
            @ApiResponse(responseCode = "400", description = "Error al emitir licencia", content = @Content)})
    @PostMapping("/issueLicense")
    public ResponseEntity<Licencia> issueLicense(@RequestBody @Valid Licencia licencia) {
        Licencia nuevaLicencia = licenciaService.emitirLicencia(licencia);
        return ResponseEntity.ok(nuevaLicencia);
    }


    @Operation(summary = "Actualizar una licencia existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Licencia actualizada exitosamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Licencia.class))}),
            @ApiResponse(responseCode = "400", description = "Licencia no encontrada", content = @Content)})
    @PutMapping("/updateLicense/{id}")
    public ResponseEntity<Licencia> updateLicense(@PathVariable Long id, @RequestBody Licencia licencia) {
        Licencia licenciaActualizada = licenciaService.actualizarLicencia(id, licencia);
        return ResponseEntity.ok(licenciaActualizada);
    }


    @Operation(summary = "Listar todas las licencias")
    @ApiResponse(responseCode = "200", description = "Listado de licencias obtenido exitosamente",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Licencia.class))})
    @GetMapping("/listLicenses")
    public List<Licencia> listLicenses() {
        return licenciaService.listarLicencias();
    }


    @Operation(summary = "Listar licencias eliminadas")
    @ApiResponse(responseCode = "200", description = "Listado de licencias eliminadas obtenido exitosamente (estado = INACTIVO & deleted = true)",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Licencia.class))})
    @GetMapping("/listDeletedLicenses")
    public List<Licencia> listDeletedLicenses() {
        return licenciaService.listarLicenciasEliminadas();
    }


    @Operation(summary = "Listar licencia por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Licencia obtenida exitosamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Licencia.class))}),
            @ApiResponse(responseCode = "400", description = "Licencia no encontrada", content = @Content)})
    @GetMapping("/listLicensesById/{id}")
    public Licencia listLicensesById(@PathVariable Long id) {
        return licenciaService.listarLicenciasPorId(id);
    }


    @Operation(summary = "Listar licencias por estado")
    @ApiResponse(responseCode = "200", description = "Listado de licencias obtenido exitosamente",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Licencia.class))})
    @GetMapping("/listLicensesByStatus/{estado}")
    public List<Licencia> listLicensesByStatus(@PathVariable String estado) {
        return licenciaService.listarLicenciasPorEstado(estado);
    }

    @Operation(summary = "Dar de baja una licencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Licencia dada de baja exitosamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BajaResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Error al dar de baja la licencia", content = @Content)
    })
    @PostMapping("/updateStatusToDeactivateLicense")
    public ResponseEntity<BajaResponse> updateStatusToDeactivateLicense(
            @RequestParam(required = false) String numeroLicencia,
            @RequestParam(required = false) Long id
    ) {
        BajaResponse licencia = licenciaService.actualizarEstadoDarDeBajaLicencia(numeroLicencia, id);
        return ResponseEntity.ok(licencia);
    }

    @Operation(summary = "Actualizar parcialmente una licencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Licencia actualizada parcialmente exitosamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Licencia.class))}),
            @ApiResponse(responseCode = "400", description = "Error al actualizar parcialmente la licencia", content = @Content)})
    @PatchMapping(path = "/partiallyUpdateLicense/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Licencia> partiallyUpdateLicense(@PathVariable Long id, @RequestBody Licencia incompleteLicencia) {
        return ResponseEntity.status(HttpStatus.OK).body(licenciaService.actualizarValorParcialesLicencia(id, incompleteLicencia));
    }


}
