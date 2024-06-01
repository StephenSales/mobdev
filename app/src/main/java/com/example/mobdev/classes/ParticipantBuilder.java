package com.example.mobdev.classes;

import java.sql.Timestamp;

public class ParticipantBuilder {
    private long id;
    private long userId;
    private long eventId;
    private String firstname;
    private String lastname;
    private String email;
    private Timestamp joinedAt;

    public ParticipantBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public ParticipantBuilder setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public ParticipantBuilder setEventId(long eventId) {
        this.eventId = eventId;
        return this;
    }

    public ParticipantBuilder setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ParticipantBuilder setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ParticipantBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ParticipantBuilder setJoinedAt(Timestamp joinedAt) {
        this.joinedAt = joinedAt;
        return this;
    }

    public Participant createParticipant() {
        return new Participant(id, userId, eventId, firstname, lastname, email, joinedAt);
    }
}