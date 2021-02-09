package com.spicecrispies.interfaces;

public interface AlbumInterface {
    String listAlbums();
    String getAlbumDetails(String isrc);
    String addAlbum(String isrc, String title, String description, int releaseYear, String artist);
    String updateAlbum(String isrc, String title, String description, int releaseYear, String artist);
    String deleteAlbum(String isrc);
}