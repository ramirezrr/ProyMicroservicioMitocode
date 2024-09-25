package com.mitocode.licencias.controller;

import com.mitocode.licencias.model.Titular;
import com.mitocode.licencias.service.ITitularService;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/titulares")
public class TitularController {

    private final ITitularService service;

    @GetMapping("/documento/{numeroDocumento}")
    public ResponseEntity<Titular> findByNumeroDocumento(@PathVariable String numeroDocumento) {
        return ResponseEntity.ok(service.findByNumeroDocumento(numeroDocumento));
    }

    @GetMapping
    public ResponseEntity<List<Titular>> getAllTitulares() {
        return ResponseEntity.ok(service.getAllTitulares());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Titular> getTitularById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTitularById(id));
    }

    @PostMapping
    public ResponseEntity<Titular> saveTitular(@RequestBody @Valid Titular titular) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveTitular(titular));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Titular> updateTitular(@PathVariable Long id, @RequestBody @Valid Titular titular) {
        titular.setId(id);
        return ResponseEntity.ok(service.updateTitular(titular));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTitular(@PathVariable Long id) {
        service.deleteTitular(id);
        return ResponseEntity.noContent().build();
    }


}