package com.mitocode.licencias.repository;

import com.mitocode.licencias.model.Licencia;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {

    Optional<List<Licencia>> findAllByDeleted(boolean deleted);
    Optional<List<Licencia>> findAllByDeletedAndEstado(boolean deleted, String estado);

    Optional<Licencia> findByDeletedAndId(boolean deleted, Long id);

    Optional<List<Licencia>> findByDeletedAndEstado(boolean deleted, String estado);


    @Query(value = "SELECT COALESCE(MAX(ID), 0) + 1 AS next_id FROM MITOCODE.LICENCIA", nativeQuery = true)
    Optional<Long> getNextLicenciaSequence();

    Optional<Licencia> findByNumLicenciaAndDeleted(String numLicencia, boolean deleted);

    @Transactional
    @Modifying
    @Query("UPDATE Licencia l SET l.deleted = true WHERE l.numLicencia = :numLicencia OR l.id = :id")
    Optional<Void> deleteByNumeroLicenceOrId(String numLicencia, Long id);


    @Query("SELECT l FROM Licencia l WHERE " +
            "(:numLicencia IS NOT NULL AND l.numLicencia = :numLicencia OR :numLicencia IS NULL) AND " +
            "(:id IS NOT NULL AND l.id = :id OR :id IS NULL) AND " +
            "l.deleted = :deleted")
    Optional<Licencia> findByNumeroLicenceOrIdAndDeleted(String numLicencia, Long id, Boolean deleted);

}