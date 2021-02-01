package entities;

import java.util.Date;

public class Album {
    private String isrc;
    private String title;
    private String description;
    private Date releasedYear;
    private String artist;

    public Album(String isrc, String title, String description, Date releasedYear, String artist) {
        this.isrc = isrc;
        this.title = title;
        this.description = description;
        this.releasedYear = releasedYear;
        this.artist = artist;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(Date releasedYear) {
        this.releasedYear = releasedYear;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
