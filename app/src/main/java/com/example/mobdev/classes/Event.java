package com.example.mobdev.classes;

import androidx.annotation.NonNull;

import java.sql.Timestamp;

public class Event {
    private final long id;
    private final String name;
    private final String description;
    private final long organizerId;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    public Event(long id, String name, String description, long organizerId, Timestamp createdAt, Timestamp updatedAt) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.organizerId = organizerId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getOrganizerId() {
        return organizerId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    @NonNull
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", organizerId=" + organizerId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
