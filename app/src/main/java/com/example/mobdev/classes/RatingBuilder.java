package com.example.mobdev.classes;

import java.sql.Timestamp;

public class RatingBuilder {
    private long id;
    private long userId;
    private long eventId;
    private int rating;
    private String message;
    private Timestamp ratedAt;

    public RatingBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public RatingBuilder setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public RatingBuilder setEventId(long eventId) {
        this.eventId = eventId;
        return this;
    }

    public RatingBuilder setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public RatingBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public RatingBuilder setRatedAt(Timestamp ratedAt) {
        this.ratedAt = ratedAt;
        return this;
    }

    public Rating createRating() {
        return new Rating(id, userId, eventId, rating, message, ratedAt);
    }
}