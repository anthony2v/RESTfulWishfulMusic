package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.entities.AlbumCover;
import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;

import java.sql.Timestamp;
import java.time.LocalDate;

public interface AlbumInterface {

    String createAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName, AlbumCover albumCover) throws RepException;
    String updateAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName, AlbumCover albumCover) throws RepException;
    String deleteAlbum(String isrc) throws RepException;
    String getAlbumInfo(String isrc) throws RepException;
    //Albums are sorted alphabetically by title.
    String listAlbums() throws RepException;
    void updateAlbumCoverImage(String isrc, AlbumCover albumCover) throws RepException;
    void deleteAlbumCoverImage(String isrc) throws RepException;
    AlbumCover getAlbumCoverImage(String isrc) throws RepException;
    //log entries are sorted chronologically by the time-stamp.
    String getChangeLogs(LocalDate fromDate, LocalDate toDate, ChangeType changeType) throws RepException;
    void clearLogs() throws RepException;
}