package com.spicecrispies.Repo_Business;

import com.spicecrispies.Repo_Core.ArtistInterface;
import com.spicecrispies.Repo_Implementation.ArtistImplementation;

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