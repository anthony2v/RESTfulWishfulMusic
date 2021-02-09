package com.spicecrispies.interfaces;

public interface ArtistInterface {
    String listArtists();
    String getArtistDetails(String nickName);
    String addArtist(String nickName, String firstName, String lastName, String autoBiography);
    String updateArtist(String nickName, String firstName, String lastName, String autoBiography);
    String deleteArtist(String nickName);
}