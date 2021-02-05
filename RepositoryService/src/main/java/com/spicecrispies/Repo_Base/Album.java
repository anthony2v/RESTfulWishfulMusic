package com.spicecrispies.Repo_Base;

public class Album {

    private String isrc;
    private String title;
    private String description;
    private int year;
    private Artist artist;

    public Album(String isrc, String title, String description, int year, Artist artist) {
        this.isrc = isrc;
        this.title = title;
        this.description = description;
        this.year = year;
        this.artist = artist;

    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }


    public String toString(){
        String description = this.description;
        if ((description == null) || description.isEmpty())
        {
            description = "No description at ths moment";
        }
        return String.format("ISRC: %s, title: %s, description: %s, release year: %d, artist: %s", isrc, title,
                description, year, artist);
    }

}

