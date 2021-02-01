package interfaces;

import entities.Album;
import entities.Artist;

import java.util.ArrayList;
import java.util.List;

public interface Respository {

     List<Album> albums = new ArrayList<>();
     List <Artist> artists = new ArrayList<>();

     String listArtists();
     String getArtistDetails(String nickName);
     boolean addArtist(String nickName, String firstName, String lastName, String autoBiography);
     boolean updateArtist(String nickName, String firstName, String lastName, String autoBiography);
     boolean deleteArtist(String nickName);


}
