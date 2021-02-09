package com.spicecrispies;

public class Album {

    private String isrc;
    private String title;
    private String description;
    private int releaseYear;
    private String artist;

    public Album(String isrc, String title, String description, int releaseYear, String artist) {
        this.isrc = isrc;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.artist = artist;

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

