package com.mitocode.licencias.repository;

import com.mitocode.licencias.model.Licencia;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LicenciaRepository extends CrudRepository<Licencia, Long> {

    Optional<List<Licencia>> findAllByDeleted(boolean deleted);

    Optional<List<Licencia>> findAllByDeletedAndEstado(boolean deleted, String estado);

    Optional<Licencia> findByDeletedAndId(boolean deleted, Long id);

    Optional<List<Licencia>> findByDeletedAndEstado(boolean deleted, String estado);
}