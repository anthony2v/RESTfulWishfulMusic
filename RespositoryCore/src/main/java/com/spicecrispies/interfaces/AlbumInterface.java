package com.spicecrispies.interfaces;

import com.spicecrispies.entities.Album;
import java.util.ArrayList;
import java.util.List;

public interface AlbumInterface {
    List<Album> albums = new ArrayList<>();
    String listAlbums();
    String getAlbumDetails(String isrc);
    String addAlbum(String isrc, String title, String description, int releaseYear, String artist);
    String updateAlbum(String isrc, String title, String description, int releaseYear, String artist);
    String deleteAlbum(String isrc);
}