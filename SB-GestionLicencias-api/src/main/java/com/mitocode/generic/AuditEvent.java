package com.mitocode.generic;


import java.io.Serial;
import java.io.Serializable;

public class AuditEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String eventId;
    private String eventType;
    private String userId;
    private String timestamp;
    private String details;

    // Constructor
    public AuditEvent(String eventId, String eventType, String userId, String timestamp, String details) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.userId = userId;
        this.timestamp = timestamp;
        this.details = details;
    }

    // Getters y Setters
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
