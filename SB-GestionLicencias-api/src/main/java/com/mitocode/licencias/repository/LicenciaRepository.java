package com.mitocode.licencias.repository;

import com.mitocode.licencias.model.Licencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {
    List<Licencia> findAllByDeleted(boolean deleted);
}