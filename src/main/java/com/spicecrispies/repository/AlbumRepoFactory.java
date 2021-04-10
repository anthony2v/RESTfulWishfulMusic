package com.spicecrispies.repository;

import com.spicecrispies.core.interfaces.AlbumRepoInterface;

public class AlbumRepoFactory {
    private static AlbumRepoInterface albumRepoInterface;

    private AlbumRepoFactory(){
        // purposefully left empty
    }

    public static synchronized AlbumRepoInterface getInstance(){
        if(albumRepoInterface == null){
            albumRepoInterface = new AlbumRepoImplementation();
        }
        return albumRepoInterface;
    }
}
