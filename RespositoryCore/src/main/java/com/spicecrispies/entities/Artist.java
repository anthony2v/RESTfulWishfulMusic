package com.spicecrispies.entities;

public class Artist {
    String nickname;
    String firstName;
    String lastName;
    String biography;

    public Artist(String nickname, String firstName, String lastName, String biography) {
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String toString(){
        return String.format("Nickname: %s, first name: %s, last name: %s, bio: %s", nickname, firstName, lastName, biography);
    }
}

