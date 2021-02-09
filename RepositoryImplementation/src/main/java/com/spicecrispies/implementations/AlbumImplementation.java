package com.spicecrispies.implementations;

import com.spicecrispies.entities.Album;
import com.spicecrispies.interfaces.AlbumInterface;

public class AlbumImplementation implements AlbumInterface {
    @Override
    public String listAlbums() {

        StringBuilder str = new StringBuilder();
        for(Album album: albums)
        {
            str.append(album.toString() + "\n");
        }

        return str.toString();
    }

    @Override
    public String getAlbumDetails(String s) {

        for(Album album: albums)
        {
            if(album.getIsrc().equalsIgnoreCase(s))
            {
                return album.toString();
            }
        }

        return "";
    }


    @Override
    public boolean addAlbum(String isrc, String title, String description, int releaseYear){

        if(getAlbumDetails(isrc).equalsIgnoreCase("")) {
            //add album (adds a new album to the collection; no artist details) thats why i put null
            albums.add(new Album(isrc,title,description, releaseYear,null));
        }
        return true;
    }

    @Override
    public boolean updateAlbum(String isrc, String title, String description, int releaseYear, String artist) {
        for(Album album: albums)
        {
            if(album.getIsrc().equalsIgnoreCase(isrc))
            {
                album.setTitle(title);
                album.setDescription(description);
                album.setReleaseYear(releaseYear);
                album.setArtist(artist);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteAlbum(String isrc) {

        int index = -1;
        for(
                Album album: albums)
        {
            if(album.getIsrc().equalsIgnoreCase(isrc))
            {
                index = albums.indexOf(album);
            }
        }

        if(index != -1){
            albums.remove(index);
            return true;
        }

        return false;
    }
}
