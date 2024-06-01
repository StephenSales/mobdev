package com.example.mobdev.classes;

import androidx.annotation.NonNull;

public class Tag {
    private final long id;
    private final String name;

    public Tag(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @NonNull
    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
