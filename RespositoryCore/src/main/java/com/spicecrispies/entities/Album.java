package com.spicecrispies.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="album")
@XmlAccessorType(XmlAccessType.FIELD)
public class Album {
    @XmlElement
    private String isrc;
    @XmlElement
    private String title;
    @XmlElement
    private String description;
    @XmlElement
    private int releaseYear;
    @XmlElement
    private String artist;

    public Album(String isrc, String title, String description, int releaseYear, String artist) {
        this.isrc = isrc;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.artist = artist;
    }

    // Copy constructor
    public Album(Album album) {
        this.isrc = album.getIsrc();
        this.title = album.getTitle();
        this.description = album.getDescription();
        this.releaseYear = album.getReleaseYear();
        this.artist = album.getArtist();
    }

    // Default constructor for code injection
    public Album() {
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }


    public String toString(){
        String description = this.description;
        if ((description == null) || description.isEmpty())
        {
            description = "No description at ths moment";
        }
        return String.format("ISRC: %s, title: %s, description: %s, release year: %d, artist: %s", isrc, title,
                description, releaseYear, artist);
    }


}

