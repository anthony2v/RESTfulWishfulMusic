package com.spicecrispies.interfaces;

//TODO: Not used in this assignment?
public interface ArtistInterface {
    String listArtists() throws Exception;
    String getArtistDetails(String nickName) throws Exception;
    String addArtist(String nickName, String firstName, String lastName, String autoBiography) throws Exception;
    String updateArtist(String nickName, String firstName, String lastName, String autoBiography) throws Exception;
    String deleteArtist(String nickName) throws Exception;
}