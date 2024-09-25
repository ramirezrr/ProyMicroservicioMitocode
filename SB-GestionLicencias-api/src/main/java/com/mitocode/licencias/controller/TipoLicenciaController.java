package com.mitocode.licencias.controller;

import com.mitocode.licencias.model.TipoLicencia;
import com.mitocode.licencias.service.ITipoLicenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/tipos-licencia")
public class TipoLicenciaController {

    private final ITipoLicenciaService service;


    @GetMapping
    public ResponseEntity<List<TipoLicencia>> getAllTipoLicencias() {
        return ResponseEntity.ok(service.getAllTipoLicencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoLicencia> getTipoLicenciaById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTipoLicenciaById(id));
    }

    @PostMapping
    public ResponseEntity<TipoLicencia> saveTipoLicencia(@RequestBody TipoLicencia tipoLicencia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveTipoLicencia(tipoLicencia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoLicencia> updateTipoLicencia(@PathVariable Long id, @RequestBody TipoLicencia tipoLicencia) {
        tipoLicencia.setId(id);
        return ResponseEntity.ok(service.updateTipoLicencia(tipoLicencia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoLicencia(@PathVariable Long id) {
        service.deleteTipoLicencia(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<TipoLicencia> findByCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(service.findByCategoria(categoria));
    }

    @GetMapping("/categoriaContiene")
    public ResponseEntity<List<TipoLicencia>> findByCategoriaContaining(@RequestParam String categoria) {
        return ResponseEntity.ok(service.findByCategoriaContaining(categoria));
    }
}