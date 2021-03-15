package com.spicecrispies.repository;


import com.spicecrispies.core.interfaces.AlbumInterface;

public class AlbumFactory {

    private static AlbumInterface albumInterface;

    private AlbumFactory(){}

    public static synchronized AlbumInterface getInstance(){
        if(albumInterface == null){
            albumInterface = new AlbumImplementation();
        }
        return albumInterface;
    }
}