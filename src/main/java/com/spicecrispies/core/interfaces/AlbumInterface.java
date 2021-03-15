package com.spicecrispies.core.interfaces;

import com.spicecrispies.core.entities.AlbumCover;
import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;

import java.sql.Timestamp;

public interface AlbumInterface {

    String createAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName, AlbumCover albumCover) throws RepException;
    String updateAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName, AlbumCover albumCover) throws RepException;
    String deleteAlbum(String isrc) throws RepException;
    String getAlbumInfo(String isrc) throws RepException;
    //Albums are sorted alphabetically by title.
    String listAlbums() throws RepException;
    String updateAlbumCoverImage(String isrc, AlbumCover albumCover) throws RepException;;
    String deleteAlbumCoverImage(String isrc) throws RepException;;
    AlbumCover getAlbumCoverImage(String isrc) throws RepException;
    //log entries are sorted chronologically by the time-stamp.
    String getChangeLogs(Timestamp fromDate, Timestamp toDate, ChangeType changeType) throws RepException;
    void clearLogs() throws RepException;
}