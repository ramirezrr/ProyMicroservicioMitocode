package com.mitocode.licencias.controller;

import com.mitocode.licencias.exception.FunctionalGenericResponse;
import com.mitocode.licencias.model.BajaResponse;
import com.mitocode.licencias.model.Licencia;
import com.mitocode.licencias.service.LicenciaServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/licencias")
public class LicenciaController {

    private final LicenciaServiceImpl licenciaService;

    @PostMapping("/estado/{numeroLicencia}")
    public ResponseEntity<FunctionalGenericResponse> validarEstadoLicencia(@PathVariable String numeroLicencia) {
        FunctionalGenericResponse nuevaLicencia = licenciaService.validarEstadoLicencia(numeroLicencia);
        return ResponseEntity.ok(nuevaLicencia);
    }

    @PostMapping
    public ResponseEntity<Licencia> emitirLicencia(@RequestBody @Valid Licencia licencia) {
        Licencia nuevaLicencia = licenciaService.emitirLicencia(licencia);
        return ResponseEntity.ok(nuevaLicencia);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Licencia> actualizarLicencia(@PathVariable Long id, @RequestBody Licencia licencia) {
        Licencia licenciaActualizada = licenciaService.actualizarLicencia(id, licencia);
        return ResponseEntity.ok(licenciaActualizada);
    }

    @GetMapping
    public List<Licencia> listarLicencias() {
        return licenciaService.listarLicencias();
    }

    @GetMapping("/eliminadas")
    public List<Licencia> listarLicenciasEliminadas() {
        return licenciaService.listarLicenciasEliminadas();
    }

    @GetMapping("/{id}")
    public Licencia listarLicenciasPorId(@PathVariable Long id) {
        return licenciaService.listarLicenciasPorId(id);
    }


    @GetMapping("listarXEstado/{estado}")
    public List<Licencia> listarLicenciasPorEstado(@PathVariable String estado) {
        return licenciaService.listarLicenciasPorEstado(estado);
    }


    @PostMapping("/baja")
    public ResponseEntity<BajaResponse> actualizarEstadoDarDeBajaLicencia(
            @RequestParam(required = false) String numeroLicencia,
            @RequestParam(required = false) Long id
    ) {

        BajaResponse licencia = licenciaService.actualizarEstadoDarDeBajaLicencia(numeroLicencia, id);
        return ResponseEntity.ok(licencia);
    }

    @PatchMapping(path = "/patch/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Licencia> actualizarLicenciaParcialmente(@PathVariable Long id, @RequestBody Licencia incompleteLicencia) {
        return ResponseEntity.status(HttpStatus.OK).body(licenciaService.actualizarValorParcialesLicencia(id, incompleteLicencia));
    }


}
