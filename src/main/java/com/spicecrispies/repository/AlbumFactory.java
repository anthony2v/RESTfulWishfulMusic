package com.spicecrispies.repository;


import com.spicecrispies.core.interfaces.AlbumRepoInterface;

public class AlbumFactory {

    private static AlbumRepoInterface albumRepoInterface;

    private AlbumFactory(){}

    public static synchronized AlbumRepoInterface getInstance(){
        if(albumRepoInterface == null){
            albumRepoInterface = new AlbumRepoImplementation();
        }
        return albumRepoInterface;
    }
}