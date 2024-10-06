package com.mitocode.microservices.audit_service.repository;

import com.mitocode.microservices.audit_service.model.Licencia;
import org.springframework.data.repository.CrudRepository;

public interface LicenciaRepository extends CrudRepository<Licencia, Long> {
}
