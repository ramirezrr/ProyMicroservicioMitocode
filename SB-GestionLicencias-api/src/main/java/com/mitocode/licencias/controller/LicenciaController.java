package com.mitocode.licencias.controller;

import com.mitocode.licencias.model.Licencia;
import com.mitocode.licencias.service.LicenciaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/licencias")
public class LicenciaController {

    private final LicenciaServiceImpl licenciaService;

    @PostMapping
    public ResponseEntity<Licencia> emitirLicencia(@RequestBody Licencia licencia) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLicencia(@PathVariable Long id) {
        licenciaService.eliminarLogicamenteLicencia(id);
        return ResponseEntity.noContent().build();
    }
}
