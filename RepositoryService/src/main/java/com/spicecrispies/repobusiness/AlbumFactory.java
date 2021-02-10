package com.spicecrispies.repobusiness;

import com.spicecrispies.interfaces.AlbumInterface;
import com.spicecrispies.implementations.AlbumImplementation;

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