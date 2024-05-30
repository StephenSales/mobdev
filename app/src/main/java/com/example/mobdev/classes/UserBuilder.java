package com.example.mobdev.classes;

import java.sql.Timestamp;

public class UserBuilder {
    private long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public UserBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserBuilder setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
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
        return new User(id, username, password, firstname, lastname, email, createdAt, updatedAt);
    }
}