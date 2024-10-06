package com.mitocode.licencias.service;

import com.mitocode.licencias.exception.GenericoResponse;
import com.mitocode.licencias.model.BajaResponse;
import com.mitocode.licencias.model.Licencia;

import java.util.List;

public interface ILicenciaService {


    List<Licencia> listarLicencias();

    Licencia listarLicenciasPorId(Long id);

    List<Licencia> listarLicenciasPorEstado(String estado);

    List<Licencia> listarLicenciasEliminadas();

}
