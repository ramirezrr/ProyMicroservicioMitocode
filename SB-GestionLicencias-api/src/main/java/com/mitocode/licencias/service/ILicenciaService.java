package com.mitocode.licencias.service;

import com.mitocode.licencias.exception.FunctionalGenericResponse;
import com.mitocode.licencias.exception.GenericoResponse;
import com.mitocode.licencias.model.BajaResponse;
import com.mitocode.licencias.model.Licencia;

import java.util.List;

public interface ILicenciaService {

    GenericoResponse validarEstadoLicencia(String licencia);

    Licencia emitirLicencia(Licencia licencia);

    Licencia actualizarLicencia(Long id, Licencia licenciaActualizada);

    List<Licencia> listarLicencias();

    Licencia listarLicenciasPorId(Long id);

    List<Licencia> listarLicenciasPorEstado(String estado);

    List<Licencia> listarLicenciasEliminadas();

    BajaResponse actualizarEstadoDarDeBajaLicencia(String numeroLicencia, Long id);

    Licencia actualizarValorParcialesLicencia(Long id, Licencia licenciaActualizada);

}
