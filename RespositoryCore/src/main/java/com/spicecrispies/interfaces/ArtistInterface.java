package com.spicecrispies.interfaces;

import com.spicecrispies.entities.Artist;
import java.util.ArrayList;
import java.util.List;

public interface ArtistInterface {
    List <Artist> artists = new ArrayList<>();
    String listArtists();
    String getArtistDetails(String nickName);
    String addArtist(String nickName, String firstName, String lastName, String autoBiography);
    String updateArtist(String nickName, String firstName, String lastName, String autoBiography);
    String deleteArtist(String nickName);
}