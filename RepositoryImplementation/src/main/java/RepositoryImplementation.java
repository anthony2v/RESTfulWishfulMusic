import entities.Artist;
import interfaces.Respository;

public class RepositoryImplementation  implements Respository {
    @Override
    public String listArtists() {

        StringBuilder str = new StringBuilder();
        for(Artist artist: artists)
        {
            str.append(artist.toString() + "\n");
        }

        return str.toString();
    }

    @Override
    public String getArtistDetails(String s) {

        for(Artist artist: artists)
        {
            if(artist.getNickName().equalsIgnoreCase(s))
            {
                return artist.toString();
            }
        }

        return "";
    }

    @Override
    public boolean addArtist(String nickName, String firstName, String lastName, String autoBiography) {

        if(getArtistDetails(nickName).equalsIgnoreCase("")) {
            artists.add(new Artist(nickName, firstName, lastName, autoBiography));
        }
        return true;
    }

    @Override
    public boolean updateArtist(String nickName, String firstName, String lastName, String autoBiography) {

        for(Artist artist: artists)
        {
            if(artist.getNickName().equalsIgnoreCase(nickName))
            {
                artist.setFirstName(firstName);
                artist.setLastName(lastName);
                artist.setAutoBiography(autoBiography);

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteArtist(String nickName) {

        int index = -1;
        for(Artist artist: artists)
        {
            if(artist.getNickName().equalsIgnoreCase(nickName))
            {
               index = artists.indexOf(artist);
            }
        }

        if(index != -1){
            artists.remove(index);
            return true;
        }

        return false;
    }
}
