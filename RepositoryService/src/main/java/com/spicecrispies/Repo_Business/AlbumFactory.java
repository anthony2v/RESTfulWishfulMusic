package com.spicecrispies.Repo_Business;
import com.spicecrispies.Repo_Core.AlbumInterface;
import com.spicecrispies.Repo_Implementation.AlbumImplementation;

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