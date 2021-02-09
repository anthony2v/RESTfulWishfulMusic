package com.spicecrispies;


import java.util.ArrayList;
import java.util.List;

public interface AlbumInterface {
    List<Album> albums = new ArrayList<>();
    String listAlbums();

    String getAlbumDetails(String isrc);
    boolean addAlbum(String isrc, String title, String description, int releaseYear);
    boolean updateAlbum(String isrc, String title, String description, int releaseYear, Artist artist);
    boolean deleteAlbum(String isrc);

}