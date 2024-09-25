package com.mitocode.licencias.service;

import com.mitocode.licencias.exception.ErrorGenericException;
import com.mitocode.licencias.model.TipoLicencia;
import com.mitocode.licencias.repository.TipoLicenciaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class TipoLicenciaServiceImpl implements ITipoLicenciaService {

    private final TipoLicenciaRepository tipoLicenciaRepository;

    @Override
    public List<TipoLicencia> getAllTipoLicencias() {
        return tipoLicenciaRepository.findAll();
    }

    @Override
    public TipoLicencia getTipoLicenciaById(Long id) {
        return tipoLicenciaRepository.findById(id).orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "No se encontró el tipo de licencia con id " + id));
    }

    @Override
    public TipoLicencia saveTipoLicencia(TipoLicencia tipoLicencia) {
        return tipoLicenciaRepository.save(tipoLicencia);
    }

    @Override
    public TipoLicencia updateTipoLicencia(TipoLicencia tipoLicencia) {
        TipoLicencia tipoLicenciaExistente = tipoLicenciaRepository.findById(tipoLicencia.getId()).orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "No se encontró el tipo de licencia con id " + tipoLicencia.getId()));
        tipoLicenciaExistente.setCategoria(tipoLicencia.getCategoria());
        tipoLicenciaExistente.setRestricciones(tipoLicencia.getRestricciones());
        return tipoLicenciaRepository.save(tipoLicenciaExistente);
    }

    @Override
    public void deleteTipoLicencia(Long id) {
        TipoLicencia tipoLicencia = tipoLicenciaRepository.findById(id).orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "No se encontró el tipo de licencia con id " + id));
        tipoLicenciaRepository.delete(tipoLicencia);
    }

    @Override
    public TipoLicencia findByCategoria(String categoria) {
        return tipoLicenciaRepository.findByCategoria(categoria).orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "No se encontró la categoría: " + categoria));
    }

    @Override
    public List<TipoLicencia> findByCategoriaContaining(String categoria) {
        List<TipoLicencia> tipoLicencias = tipoLicenciaRepository.findByCategoriaContaining(categoria);
        if (tipoLicencias.isEmpty()) {
            throw new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "No se encontró la categoría que contengan: " + categoria);
        }
        return tipoLicencias;
    }
}
