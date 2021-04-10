package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.entities.Album;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AlbumRepoInterface {
    boolean createAlbum(Album album) throws ClassNotFoundException, InterruptedException, SQLException;
    boolean deleteAlbum(String isrc) throws ClassNotFoundException, InterruptedException, SQLException;
    Album getAlbumInfo(String isrc) throws ClassNotFoundException, SQLException;
    // Albums are sorted alphabetically by title.
    ArrayList<Album> listAlbums() throws ClassNotFoundException, SQLException;
    //log entries are sorted chronologically by the time-stamp.
//    String getChangeLogs(LocalDate fromDate, LocalDate toDate, ChangeType changeType) throws RepException;
//    void clearLogs() throws RepException;
}