package com.mitocode.licencias.service;

import com.mitocode.licencias.model.Titular;

import java.util.List;

public interface ITitularService {

    Titular findByNumeroDocumento(String numerodocumento);

    List<Titular> getAllTitulares();

    Titular getTitularById(Long id);

    Titular saveTitular(Titular titular);

    Titular updateTitular(Titular titular);

    void deleteTitular(Long id);

}
