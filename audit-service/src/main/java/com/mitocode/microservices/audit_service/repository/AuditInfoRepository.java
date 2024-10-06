package com.mitocode.microservices.audit_service.repository;

import com.mitocode.generic.AuditEvent;
import org.springframework.data.repository.CrudRepository;

public interface AuditInfoRepository extends CrudRepository<AuditEvent, Long> {
}
