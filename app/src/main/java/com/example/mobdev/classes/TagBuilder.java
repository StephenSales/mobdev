package com.example.mobdev.classes;

public class TagBuilder {
    private long id;
    private String name;

    public TagBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public TagBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Tag createTag() {
        return new Tag(id, name);
    }
}