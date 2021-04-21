package com.spicecrispies.core.entities;

import java.security.SecureRandom;

public class User {

    private static final int TOKEN_LENGTH = 20;
    private String id;
    private String name;
    private String password;
    private String token;

    public User() {
        // purposefully left empty
    }

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.token = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getToken() {
        return this.token;
    }

    public void generateToken() {
        String number = "0123456789";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = lower.toUpperCase();
        String randomString = lower + upper + number;

        StringBuilder sb = new StringBuilder(TOKEN_LENGTH);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(randomString.length());
            char rndChar = randomString.charAt(rndCharAt);
            sb.append(rndChar);
        }
        this.token = sb.toString();
    }

    public void destroyToken(){
        this.token = "";
    }
}
