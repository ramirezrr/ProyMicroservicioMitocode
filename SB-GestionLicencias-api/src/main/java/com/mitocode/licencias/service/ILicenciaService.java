package com.mitocode.licencias.service;

import com.mitocode.licencias.model.Licencia;

import java.util.List;

public interface ILicenciaService {

    Licencia emitirLicencia(Licencia licencia);

    Licencia actualizarLicencia(Long id, Licencia licenciaActualizada);

    List<Licencia> listarLicencias();

    void eliminarLogicamenteLicencia(Long id);

}
