package com.mitocode.licencias.repository;

import com.mitocode.licencias.model.Titular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TitularRepository extends JpaRepository<Titular, Long> {

    Optional<Titular> findByNumeroDocumento(String numeroDocumento);
}