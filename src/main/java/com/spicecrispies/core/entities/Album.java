package com.spicecrispies.core.entities;

public class Album {
    private String isrc;
    private String title;
    private String description;
    private int releaseYear;
    private String artistFirstName;
    private String artistLastName;
    private AlbumCover albumCover;

    public Album(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName, AlbumCover albumCover) {
        this.isrc = isrc;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.artistFirstName = artistFirstName;
        this.artistLastName = artistLastName;
        this.albumCover = albumCover;
    }

    // Copy constructor
    public Album(Album album) {
        this.isrc = album.getIsrc();
        this.title = album.getTitle();
        this.description = album.getDescription();
        this.releaseYear = album.getReleaseYear();
        this.artistFirstName = album.getArtistFirstName();
        this.artistLastName = album.getArtistLastName();
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

    public String getArtistFirstName() {
        return artistFirstName;
    }

    public void setArtistFirstName(String artistFirstName) {
        this.artistFirstName = artistFirstName;
    }

    public String getArtistLastName() {
        return artistLastName;
    }

    public void setArtistLastName(String artistLastName) {
        this.artistLastName = artistLastName;
    }

    public AlbumCover getAlbumCover() {
        return albumCover;
    }

    public void setAlbumCover(AlbumCover albumCover) {
        this.albumCover = albumCover;
    }

    public String toString(){
        String description = this.description;
        if ((description == null) || description.isEmpty())
        {
            description = "No description at ths moment";
        }
        return String.format("ISRC: %s, title: %s, description: %s, release year: %d, artist: %s", isrc, title,
                description, releaseYear, artistFirstName + " " + artistLastName);
    }


}



