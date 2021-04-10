package com.spicecrispies.core.entities;

public class Album {
    private String isrc;
    private String title;
    private int releaseYear;
    private String artist;

    public Album(String isrc, String title, int releaseYear, String artist) {
        this.isrc = isrc;
        this.title = title;
        this.releaseYear = releaseYear;
        this.artist = artist;
    }

    // Copy constructor
    public Album(Album album) {
        this.isrc = album.getIsrc();
        this.title = album.getTitle();
        this.releaseYear = album.getReleaseYear();
        this.artist = album.getArtist();
    }

    // Default constructor for code injection
    public Album() {
        // purposefully left empty
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return String.format("ISRC: %s, title: %s, release year: %d, artist: %s", isrc, title, releaseYear, artist);
    }
}
