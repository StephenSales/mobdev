package com.example.mobdev.classes;

import java.sql.Time;
import java.sql.Timestamp;

public class EventBuilder {
    private long id;
    private String name;
    private String description;
    private String location;
    private double price;
    private long organizerId;
    private Timestamp eventDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public EventBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public EventBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public EventBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public EventBuilder setLocation(String description) {
        this.location = location;
        return this;
    }

    public EventBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public EventBuilder setOrganizerId(long organizerId) {
        this.organizerId = organizerId;
        return this;
    }

    public EventBuilder setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    public EventBuilder setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public EventBuilder setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Event createEvent() {
        return new Event(id, name, description, location, price, eventDate, organizerId, createdAt, updatedAt);
    }
}