package com.spicecrispies;


import java.util.ArrayList;
import java.util.List;


public interface ArtistInterface {


    List <Artist> artists = new ArrayList<>();

    String listArtists();
    String getArtistDetails(String nickName);
    boolean addArtist(String nickName, String firstName, String lastName, String autoBiography);
    boolean updateArtist(String nickName, String firstName, String lastName, String autoBiography);
    boolean deleteArtist(String nickName);


}