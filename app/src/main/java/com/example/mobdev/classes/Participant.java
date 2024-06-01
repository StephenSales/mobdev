package com.example.mobdev.classes;

import androidx.annotation.NonNull;

import java.sql.Timestamp;

public class Participant {
    private final long id;
    private final long userId;
    private final long eventId;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final Timestamp joinedAt;

    public Participant(long id, long userId, long eventId, String firstname, String lastname, String email, Timestamp joinedAt) {

        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.joinedAt = joinedAt;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getEventId() {
        return eventId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getJoinedAt() {
        return joinedAt;
    }

    @NonNull
    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", userId=" + userId +
                ", eventId=" + eventId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", joinedAt=" + joinedAt +
                '}';
    }
}
