package com.spicecrispies.repository;

import com.spicecrispies.core.entities.Album;
import com.spicecrispies.core.entities.AlbumCover;
import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.interfaces.AlbumInterface;
import com.spicecrispies.core.logging.LogEntry;
import com.spicecrispies.core.logging.LogFault;
import com.spicecrispies.persistence.AlbumMapper;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;

public class AlbumImplementation implements AlbumInterface, Serializable {
    private static final int MAX_AVAILABLE = 1;
    private static final Semaphore sema = new Semaphore(MAX_AVAILABLE, true);
    //TODO: This will be stored in the database through data layer
    //private static final ArrayList<Album> albums = new ArrayList<>();
    //private static final ArrayList<LogEntry> logs = new ArrayList<>();


    @Override
    public String createAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName, AlbumCover albumCover) throws RepException {
        String response = "";

        getLock();

        if (getAlbumInfo(isrc).equalsIgnoreCase("")) {
           // albums.add(new Album(isrc, title, description, releaseYear, artistFirstName, artistLastName, albumCover));
            AlbumMapper.insert(new Album(isrc, title, description, releaseYear, artistFirstName, artistLastName, albumCover));

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
        /*for (Album album : albums) {
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
        }*/

        AlbumMapper.update(new Album(isrc, title, description, releaseYear, artistFirstName, artistLastName, albumCover));

        releaseLock();
        addLogEntry(isrc, ChangeType.UPDATE);
        return response;
    }

    @Override
    public String deleteAlbum(String isrc) throws RepException {
        String response = "Failed deleting album. Please check ISRC.";
        getLock();
      /*  for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getIsrc().equalsIgnoreCase(isrc)) {
                albums.remove(i);
                response = "Album deleted successfully.";
                break;
            }
        }*/
        AlbumMapper.delete(isrc);
        releaseLock();
        addLogEntry(isrc, ChangeType.DELETE);
        return response;
    }

    @Override
    public String getAlbumInfo(String isrc) throws RepException {
      /*  for (Album album : albums) {
            if (album.getIsrc().equalsIgnoreCase(isrc)) {
                return album.toString();
            }
        }

       */
       return AlbumMapper.select(isrc).toString();

    }

    @Override
    public String listAlbums() throws RepException {
        StringBuilder str = new StringBuilder();
        getLock();
        /*
        for (Album album : albums) {
            str.append(album.toString()).append("\n");
        }
         */
        for (Album album : AlbumMapper.selectAll()) {
            str.append(album.toString()).append("\n");
        }

        releaseLock();
        return str.toString();
    }

    @Override
    public void updateAlbumCoverImage(String isrc, AlbumCover albumCover) throws RepException {
        Album album  = AlbumMapper.select(isrc);
        album.setAlbumCover(albumCover);
        AlbumMapper.update(album);
    }

    @Override
    public void deleteAlbumCoverImage(String isrc) throws RepException {
        Album album  = AlbumMapper.select(isrc);
        album.setAlbumCover(new AlbumCover(isrc));
        AlbumMapper.update(album);
    }

    @Override
    public AlbumCover getAlbumCoverImage(String isrc) throws RepException {
       return AlbumMapper.select(isrc).getAlbumCover();
    }

    @Override
    public String getChangeLogs(LocalDate fromDate, LocalDate toDate, ChangeType changeType) throws RepException {
        LogManagerImplementation logMapper = new LogManagerImplementation();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<LogEntry> changeLogs = null;
        try {
            changeLogs = logMapper.getChangeLogs(fromDate.format(dateFormatter), toDate.format(dateFormatter), changeType.toString());
        } catch (LogFault ex) {
            throw new RepException("LogFault exception thrown: " + ex.getMessage());
        }

        StringBuilder str = new StringBuilder();
        for (LogEntry entry : changeLogs) {
            str.append(entry.toString()).append("\n");
        }

        return str.toString();
    }

    @Override
    public void clearLogs() throws RepException {
        throw new RepException("Method not to be implemented in this assignment.");
    }


    private void addLogEntry(String recordKey, ChangeType changeType) throws RepException{
        //logs.add(new LogEntry(LocalDateTime.now(), changeType, recordKey));
        LogManagerImplementation logMapper = new LogManagerImplementation();
        logMapper.addLog(new LogEntry(LocalDateTime.now(),changeType,recordKey));
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
}