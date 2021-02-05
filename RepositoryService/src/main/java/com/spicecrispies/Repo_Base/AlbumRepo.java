package com.spicecrispies.Repo_Base;


import java.util.ArrayList;

public interface AlbumRepo {

    ArrayList<Album> listAlbum();

    Album getAlbum(String isrc);

    void  addAlbum(Album album);

    void updateAlbum(Album album);

    void deleteAlbum(String isrc);
}