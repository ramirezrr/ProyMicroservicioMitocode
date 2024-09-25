package com.mitocode.licencias.service;

import com.mitocode.licencias.exception.ErrorGenericException;
import com.mitocode.licencias.model.Titular;
import com.mitocode.licencias.repository.TitularRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Slf4j
public class TitularServiceImpl implements ITitularService {

    private final TitularRepository titularRepository;

    @Override
    public Titular findByNumeroDocumento(String numerodocumento) {
        Optional<Titular> titular = titularRepository.findByNumeroDocumento(numerodocumento);
        return titular.orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "No se encontró el titular con dni " + numerodocumento));
    }


    @Override
    public List<Titular> getAllTitulares() {
        return titularRepository.findAll();
    }

    @Override
    public Titular getTitularById(Long id) {
        return titularRepository.findById(id).orElseThrow(() -> new ErrorGenericException(HttpStatus.NOT_FOUND.toString(), "No se encontró el titular con id " + id));
    }

    @Override
    public Titular saveTitular(Titular titular) {
        return titularRepository.save(titular);
    }

    @Override
    public Titular updateTitular(Titular titular) {
        Titular titularExistente = getTitularById(titular.getId());
        titularExistente.setNombres(titular.getNombres());
        titularExistente.setApellidos(titular.getApellidos());
        titularExistente.setFechaNacimiento(titular.getFechaNacimiento());
        titularExistente.setDireccion(titular.getDireccion());
        return titularRepository.save(titularExistente);
    }

    @Override
    public void deleteTitular(Long id) {
        Titular titular = getTitularById(id);
        titularRepository.delete(titular);
    }

}
