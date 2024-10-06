package com.mitocode.microservices.audit_service.repository;

import com.mitocode.microservices.audit_service.model.AuditInfo;
import org.springframework.data.repository.CrudRepository;

public interface AuditInfoRepository extends CrudRepository<AuditInfo, Long> {
}
