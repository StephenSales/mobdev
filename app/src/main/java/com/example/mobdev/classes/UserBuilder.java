package com.example.mobdev.classes;

import java.sql.Timestamp;

public class UserBuilder {
    private long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String aboutMe;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public UserBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public UserBuilder setName(String username) {
        this.username = username;
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

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
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

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public User createUser() {
        return new User(id, username, email, password, firstName, lastName, aboutMe, createdAt, updatedAt);
    }

}