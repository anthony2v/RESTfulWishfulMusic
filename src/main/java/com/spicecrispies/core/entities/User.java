package com.spicecrispies.core.entities;

public class User {
    private String name;
    private String password;

    public User() {
        // purposefully left empty
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return String.format("Username: %s", name);
    }
}
