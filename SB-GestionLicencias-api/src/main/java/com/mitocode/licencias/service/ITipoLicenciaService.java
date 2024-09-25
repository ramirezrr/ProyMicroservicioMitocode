package com.mitocode.licencias.service;

import com.mitocode.licencias.model.TipoLicencia;

import java.util.List;

public interface ITipoLicenciaService {


    List<TipoLicencia> getAllTipoLicencias();

    TipoLicencia getTipoLicenciaById(Long id);

    TipoLicencia saveTipoLicencia(TipoLicencia tipoLicencia);

    TipoLicencia updateTipoLicencia(TipoLicencia tipoLicencia);

    void deleteTipoLicencia(Long id);

    TipoLicencia findByCategoria(String categoria);

    List<TipoLicencia> findByCategoriaContaining(String categoria);

}
