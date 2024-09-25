package com.mitocode.licencias.repository;

import com.mitocode.licencias.model.TipoLicencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoLicenciaRepository extends JpaRepository<TipoLicencia, Long> {

    Optional<TipoLicencia> findByCategoria(String categoria);

    List<TipoLicencia> findByCategoriaContaining(String categoria);

}