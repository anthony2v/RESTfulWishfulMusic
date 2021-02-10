package com.spicecrispies.implementations;

import com.spicecrispies.entities.Album;
import com.spicecrispies.interfaces.AlbumInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class AlbumImplementation implements AlbumInterface, Serializable {
    private static final int MAX_AVAILABLE = 1;
    private static final Semaphore sema = new Semaphore(MAX_AVAILABLE, true);
    private static final ArrayList<Album> albums = new ArrayList<>();

    @Override
    public String listAlbums() throws Exception {
        StringBuilder str = new StringBuilder();
        sema.acquire();
        for (Album album : albums) {
            str.append(album.toString()).append("\n");
        }
        sema.release();
        return str.toString();
    }

    @Override
    public String getAlbumDetails(String s) {
        for (Album album : albums) {
            if (album.getIsrc().equalsIgnoreCase(s)) {
                return album.toString();
            }
        }
        return "";
    }


    @Override
    public String addAlbum(String isrc, String title, String description, int releaseYear, String artist) throws Exception {
        String response = "";
        sema.acquire();
        if (getAlbumDetails(isrc).equalsIgnoreCase("")) {
            albums.add(new Album(isrc, title, description, releaseYear, artist));
            response = "Album with ISRC " + isrc +" added successfully.";
        } else {
            response = "Album with ISRC " + isrc +" already in system.";
        }
        sema.release();
        return response;
    }

    @Override
    public String updateAlbum(String isrc, String title, String description, int releaseYear, String artist) throws Exception {
        String response = "Album not updated. Verify that it has been added.";
        sema.acquire();
        for (Album album : albums) {
            if (album.getIsrc().equalsIgnoreCase(isrc)) {
                album.setTitle(title);
                album.setDescription(description);
                album.setReleaseYear(releaseYear);
                album.setArtist(artist);
                response = "Album updated successfully";
                break;
            }
        }
        sema.release();
        return response;
    }

    @Override
    public String deleteAlbum(String isrc) throws Exception {
        String response = "Failed deleting album. Please check ISRC.";
        sema.acquire();
        for (int i = 1; i < albums.size(); i++) {
            if (albums.get(i).getIsrc().equalsIgnoreCase(isrc)) {
                albums.remove(i);
                response = "Album deleted successfully.";
                break;
            }
        }
        sema.release();
        return response;
    }
}