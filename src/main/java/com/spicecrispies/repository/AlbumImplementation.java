package com.spicecrispies.repository;

import com.spicecrispies.core.entities.Album;
import com.spicecrispies.core.entities.AlbumCover;
import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.interfaces.AlbumInterface;
import com.spicecrispies.core.logging.LogEntry;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class AlbumImplementation implements AlbumInterface, Serializable {
    private static final int MAX_AVAILABLE = 1;
    private static final Semaphore sema = new Semaphore(MAX_AVAILABLE, true);
    //TODO: This will be stored in the database through data layer
    private static final ArrayList<Album> albums = new ArrayList<>();
    private static final ArrayList<LogEntry> logs = new ArrayList<>();


    @Override
    public String createAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName, AlbumCover albumCover) throws RepException {
        String response = "";

        getLock();

        if (getAlbumInfo(isrc).equalsIgnoreCase("")) {
            albums.add(new Album(isrc, title, description, releaseYear, artistFirstName, artistLastName, albumCover));
            response = "Album with ISRC " + isrc +" added successfully.";
        } else {
            response = "Album with ISRC " + isrc +" already in the system.";
        }
        releaseLock();
        addLogEntry(isrc, ChangeType.CREATE);
        return response;
    }

    @Override
    public String updateAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName, AlbumCover albumCover) throws RepException {
        String response = "Album not updated. Verify that it has been added.";
        getLock();
        for (Album album : albums) {
            if (album.getIsrc().equalsIgnoreCase(isrc)) {
                album.setTitle(title);
                album.setDescription(description);
                album.setReleaseYear(releaseYear);
                album.setArtistFirstName(artistFirstName);
                album.setArtistLastName(artistLastName);
                album.setAlbumCover(albumCover);
                response = "Album updated successfully";
                break;
            }
        }
        releaseLock();
        addLogEntry(isrc, ChangeType.UPDATE);
        return response;
    }

    @Override
    public String deleteAlbum(String isrc) throws RepException {
        String response = "Failed deleting album. Please check ISRC.";
        getLock();
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getIsrc().equalsIgnoreCase(isrc)) {
                albums.remove(i);
                response = "Album deleted successfully.";
                break;
            }
        }
        releaseLock();
        addLogEntry(isrc, ChangeType.DELETE);
        return response;
    }

    @Override
    public String getAlbumInfo(String isrc) throws RepException {
        for (Album album : albums) {
            if (album.getIsrc().equalsIgnoreCase(isrc)) {
                return album.toString();
            }
        }
        return "";
    }

    @Override
    public String listAlbums() throws RepException {
        StringBuilder str = new StringBuilder();
        getLock();
        for (Album album : albums) {
            str.append(album.toString()).append("\n");
        }
        releaseLock();
        return str.toString();
    }

    @Override
    public String updateAlbumCoverImage(String isrc, AlbumCover albumCover) throws RepException {
        return null;
    }

    @Override
    public String deleteAlbumCoverImage(String isrc) throws RepException {
        return null;
    }

    @Override
    public AlbumCover getAlbumCoverImage(String isrc) throws RepException {
        return null;
    }

    @Override
    public String getChangeLogs(LocalDate fromDate, LocalDate toDate, ChangeType changeType) throws RepException {
        return null;
    }

    @Override
    public void clearLogs() throws RepException {
        throw new RepException("Method not to be implemented in this assignment.");
    }


    private void getLock() throws RepException{
        try {
            sema.acquire();
        } catch (InterruptedException ex) {
            throw new RepException("InterruptedException raised: \r\n" + ex.toString());
        }
    }

    private void releaseLock(){
        sema.release();
    }

    private void addLogEntry(String recordKey, ChangeType changeType){
        logs.add(new LogEntry(LocalDateTime.now(), changeType, recordKey));
    }
}