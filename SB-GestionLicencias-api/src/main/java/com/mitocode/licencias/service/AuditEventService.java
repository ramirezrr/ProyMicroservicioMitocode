package com.mitocode.licencias.service;

import com.mitocode.generic.AuditEvent;
import com.mitocode.generic.GenericEntity;
import com.mitocode.licencias.model.Licencia;
import com.mitocode.licencias.util.KafkaUtil;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuditEventService {

    private final KafkaUtil kafkaUtil;

    @Async
    public void logEvent(String eventType, String details) {
        String eventId = UUID.randomUUID().toString();
        ZonedDateTime fechaActual = ZonedDateTime.now(ZoneId.of("America/Lima"));
        String timestamp = fechaActual.toString();
        String userId ="mitocode";

        AuditEvent auditEvent = new AuditEvent(eventId, eventType, userId, timestamp, details);

        GenericEntity<AuditEvent> genericEntity = new GenericEntity<>(auditEvent, AuditEvent.class.getSimpleName());

        kafkaUtil.sendMessage(genericEntity);
    }
}
