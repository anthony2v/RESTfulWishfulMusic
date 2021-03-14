package com.spicecrispies.implementations;

import com.spicecrispies.entities.Artist;
import com.spicecrispies.interfaces.ArtistInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

//TODO: Not being used in assignment 02
public class ArtistImplementation implements ArtistInterface, Serializable {
    private static final int MAX_AVAILABLE = 1;
    private static final Semaphore sema = new Semaphore(MAX_AVAILABLE, true);
    private static final ArrayList<Artist> artists = new ArrayList<>();

    @Override
    public String listArtists() throws Exception {
        StringBuilder str = new StringBuilder();
        sema.acquire();
        for (Artist artist : artists) {
            str.append(artist.toString()).append("\n");
        }
        sema.release();
        return str.toString();
    }

    @Override
    public String getArtistDetails(String s) {
        for (Artist artist : artists) {
            if (artist.getNickname().equalsIgnoreCase(s)) {
                return artist.toString();
            }
        }
        return "";
    }

    @Override
    public String addArtist(String nickName, String firstName, String lastName, String autoBiography) throws Exception {
        String response = "";
        sema.acquire();
        if (getArtistDetails(nickName).equalsIgnoreCase("")) {
            artists.add(new Artist(nickName, firstName, lastName, autoBiography));
            response = "Artist added successfully";
        } else {
            response = "Artist named " + nickName +" already in the system.";
        }
        sema.release();
        return response;
    }

    @Override
    public String updateArtist(String nickName, String firstName, String lastName, String biography) throws Exception {
        String response = "Artist not updated. Verify that it has been added.";
        sema.acquire();
        for (Artist artist : artists) {
            if (artist.getNickname().equalsIgnoreCase(nickName)) {
                artist.setFirstName(firstName);
                artist.setLastName(lastName);
                artist.setBiography(biography);
                response = "Artist updated successfully.";
                break;
            }
        }
        sema.release();
        return response;
    }

    @Override
    public String deleteArtist(String nickName) throws Exception {
        String response = "Failed deleting artist. Please check nickname.";
        sema.acquire();
        for (int i = 0; i < artists.size(); i++) {
            if (artists.get(i).getNickname().equalsIgnoreCase(nickName)) {
                artists.remove(i);
                response = "Artist deleted successfully.";
                break;
            }
        }
        sema.release();
        return response;
    }
}