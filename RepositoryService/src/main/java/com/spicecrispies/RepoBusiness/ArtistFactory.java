package com.spicecrispies.RepoBusiness;

import com.spicecrispies.interfaces.ArtistInterface;
import com.spicecrispies.ArtistImplementation;

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