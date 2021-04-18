package com.spicecrispies.core.entities;

public class Album {
    private String id;
    private String title;
    private int releaseYear;
    private String artist;

    public Album(String id, String title, int releaseYear, String artist) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.artist = artist;
    }

    // Copy constructor
    public Album(Album album) {
        this.id = album.getId();
        this.title = album.getTitle();
        this.releaseYear = album.getReleaseYear();
        this.artist = album.getArtist();
    }

    // Default constructor for code injection
    public Album() {
        // purposefully left empty
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return String.format("ID: %s, title: %s, release year: %d, artist: %s", id, title, releaseYear, artist);
    }
}
