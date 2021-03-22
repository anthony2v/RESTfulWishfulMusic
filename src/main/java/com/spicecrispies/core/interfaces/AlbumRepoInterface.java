package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.entities.Album;
import com.spicecrispies.core.exceptions.RepException;

import java.util.ArrayList;

public interface AlbumRepoInterface {

    String createAlbum(Album album) throws RepException;
    Album updateAlbum(Album album) throws RepException;
    String deleteAlbum(String isrc) throws RepException;
    Album getAlbumInfo(String isrc) throws RepException;
    //Albums are sorted alphabetically by title.
    ArrayList<Album> listAlbums() throws RepException;
    //log entries are sorted chronologically by the time-stamp.
//    String getChangeLogs(LocalDate fromDate, LocalDate toDate, ChangeType changeType) throws RepException;
//    void clearLogs() throws RepException;
}