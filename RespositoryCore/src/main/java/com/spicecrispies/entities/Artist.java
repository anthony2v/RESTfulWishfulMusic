package com.spicecrispies.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="artist")
@XmlAccessorType(XmlAccessType.FIELD)
public class Artist {
    @XmlElement
    String nickname;
    @XmlElement
    String firstName;
    @XmlElement
    String lastName;
    @XmlElement
    String biography;

    public Artist(String nickname, String firstName, String lastName, String biography) {
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
    }

    // Copy constructor
    public Artist(Artist artist) {
        this.nickname = artist.getNickname();
        this.firstName = artist.getFirstName();
        this.lastName = artist.getLastName();
        this.biography = artist.getBiography();
    }

    // Default constructor for code injection
    public Artist() {
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

