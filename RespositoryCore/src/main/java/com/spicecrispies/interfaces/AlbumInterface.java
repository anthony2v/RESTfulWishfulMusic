package com.spicecrispies.interfaces;

public interface AlbumInterface {
    String listAlbums() throws Exception;
    String getAlbumDetails(String isrc) throws Exception;
    String addAlbum(String isrc, String title, String description, int releaseYear, String artist) throws Exception;
    String updateAlbum(String isrc, String title, String description, int releaseYear, String artist) throws Exception;
    String deleteAlbum(String isrc) throws Exception;
}