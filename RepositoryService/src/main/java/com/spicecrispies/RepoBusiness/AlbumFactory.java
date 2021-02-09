package com.spicecrispies.RepoBusiness;

import com.spicecrispies.AlbumInterface;
import com.spicecrispies.AlbumImplementation;

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