package com.example.mobdev.classes;

import androidx.annotation.NonNull;

import java.sql.Timestamp;

public class User {

    private final long id;
    private final String name;
    private final String email;
    private final String password;
    private final String aboutMe;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    public User(long id, String name, String email, String password, String aboutMe, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.aboutMe = aboutMe;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAboutMe() {
        return aboutMe;
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
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
