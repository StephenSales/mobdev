package com.example.mobdev.classes;

import java.sql.Timestamp;

public class UserBuilder {
    private long id;
    private String name;
    private String email;
    private String password;
    private String aboutMe;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public UserBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }
    public UserBuilder setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
        return this;
    }

    public UserBuilder setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public UserBuilder setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public User createUser() {
        return new User(id, name, email, password, aboutMe, createdAt, updatedAt);
    }
}