package com.spicecrispies.repository;

import com.spicecrispies.core.interfaces.ArtistInterface;



public class ArtistFactory {

    private static ArtistInterface artistInterface;

    private ArtistFactory(){}

    public static synchronized ArtistInterface getInstance(){
        if(artistInterface == null){
            artistInterface= new ArtistImplementation();
        }
        return artistInterface;
    }
}