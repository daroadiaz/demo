package com.example.demo.event;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventGridEvent<T> {
    private String id;
    private String eventType;
    private String subject;
    private LocalDateTime eventTime;
    private T data;
    private String dataVersion;

    public EventGridEvent() {
        this.id = UUID.randomUUID().toString();
        this.eventTime = LocalDateTime.now();
        this.dataVersion = "1.0";
    }

    public EventGridEvent(String eventType, String subject, T data) {
        this();
        this.eventType = eventType;
        this.subject = subject;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(String dataVersion) {
        this.dataVersion = dataVersion;
    }
}
