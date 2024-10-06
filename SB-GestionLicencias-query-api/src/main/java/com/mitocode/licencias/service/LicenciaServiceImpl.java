package com.mitocode.licencias.service;

import com.mitocode.generic.GenericEntity;
import com.mitocode.licencias.exception.ErrorGenericException;
import com.mitocode.licencias.model.Licencia;
import com.mitocode.licencias.repository.LicenciaRepository;
import com.mitocode.licencias.util.KafkaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Slf4j
public class LicenciaServiceImpl implements ILicenciaService {


    private final LicenciaRepository licenciaRepository;

    private final KafkaUtil kafkaUtil;


    public List<Licencia> listarLicencias() {

        Optional<List<Licencia>> findAllByDeleted = licenciaRepository.findAllByDeleted(false);

        for (Licencia licencia : findAllByDeleted.get()) {


            GenericEntity<Licencia> genericEntity = new GenericEntity<>(licencia, Licencia.class.getSimpleName());


            kafkaUtil.sendMessage(genericEntity);
        }

        return findAllByDeleted.orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "Error al obtener licencias."));
    }

    public Licencia listarLicenciasPorId(Long id) {
        return licenciaRepository.findByDeletedAndId(false, id).orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "Licencia no encontrada id: " + id));
    }

    public List<Licencia> listarLicenciasPorEstado(String estado) {
        return licenciaRepository.findByDeletedAndEstado(false, estado).orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "Licencias no encontradas con estado: " + estado));
    }

    public List<Licencia> listarLicenciasEliminadas() {
        return licenciaRepository.findAllByDeletedAndEstado(true, "INACTIVO").orElseThrow(() -> new ErrorGenericException(HttpStatus.BAD_REQUEST.toString(), "Error al obtener licencias"));
    }

}
