package com.spicecrispies.Repo_Base;

public class Album {

    private String isrc;
    private String title;
    private String description;
    private int year;
    private Artist artist;

    public Album(){}

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

}
