package com.mitocode.licencias.service;

import com.mitocode.licencias.model.EstadoLicencia;
import com.mitocode.licencias.model.Licencia;
import com.mitocode.licencias.repository.LicenciaRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LicenciaServiceImpl implements ILicenciaService {

    private final LicenciaRepository licenciaRepository;

    public Licencia emitirLicencia(Licencia licencia) {
        licencia.setFechaEmision(LocalDate.now());
        licencia.setEstado(EstadoLicencia.ACTIVO);
        return licenciaRepository.save(licencia);
    }

    public Licencia actualizarLicencia(Long id, Licencia licenciaActualizada) {
        Licencia licencia = licenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Licencia no encontrada"));
        // Aquí realizas las actualizaciones parciales
        return licenciaRepository.save(licencia);
    }

    public List<Licencia> listarLicencias() {
        return licenciaRepository.findAllByDeleted(false);
    }

    public void eliminarLogicamenteLicencia(Long id) {
        Licencia licencia = licenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Licencia no encontrada"));
        licencia.setDeleted(true);
        licenciaRepository.save(licencia);
    }
}
