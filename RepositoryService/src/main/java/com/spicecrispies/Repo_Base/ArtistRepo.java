package com.spicecrispies.Repo_Base;


import java.util.ArrayList;


public interface ArtistRepo {

    ArrayList<Artist> listArtist();

    Artist getArtist(String nickname);

    void addArtist(Artist artist);

    void updateArtist(Artist artist);

    void deleteArtist(String nickname);


}