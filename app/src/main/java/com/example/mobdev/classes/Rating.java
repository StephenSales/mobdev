package com.example.mobdev.classes;

import androidx.annotation.NonNull;

import java.sql.Timestamp;

public class Rating {

    private final long id;
    private final long userId;
    private final long eventId;
    private final int rating;
    private final String message;
    private final Timestamp ratedAt;

    public Rating(long id, long userId, long eventId, int rating, String message, Timestamp ratedAt) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.rating = rating;
        this.message = message;
        this.ratedAt = ratedAt;
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

    public int getRating() {
        return rating;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getRatedAt() {
        return ratedAt;
    }

    @NonNull
    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", userId=" + userId +
                ", eventId=" + eventId +
                ", rating=" + rating +
                ", message='" + message + '\'' +
                ", ratedAt=" + ratedAt +
                '}';
    }
}
