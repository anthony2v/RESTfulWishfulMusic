package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.entities.Album;
import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;

import java.sql.Timestamp;
import java.time.LocalDate;

public interface AlbumRepoInterface {

    String createAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName) throws RepException;
    String updateAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName) throws RepException;
    String deleteAlbum(String isrc) throws RepException;
    Album getAlbumInfo(String isrc) throws RepException;
    //Albums are sorted alphabetically by title.
    String listAlbums() throws RepException;
    //log entries are sorted chronologically by the time-stamp.
//    String getChangeLogs(LocalDate fromDate, LocalDate toDate, ChangeType changeType) throws RepException;
//    void clearLogs() throws RepException;
}