package servlet;

import com.spicecrispies.RepoBusiness.ArtistFactory;
import com.spicecrispies.ArtistInterface;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "artist")
public class ArtistServlet extends HttpServlet {

    private ArtistInterface artistInterface = ArtistFactory.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String nickname = request.getParameter("nickname");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String biography = request.getParameter("bio");

        if(biography == null)
        {
            biography = "No biography at this moment";
        }

        try{
            if(nickname== null && firstName == null && lastName == null) {
                System.out.println("Artist ERROR");
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
            else
            {
                if(artistInterface.addArtist(nickname,firstName,lastName,biography)) {
                    System.out.println("Artist ADDED");
                    httpResponse.setStatus(HttpServletResponse.SC_OK);

                }
                else
                {   //Artist not found
                    System.out.println("Artist NOT ADDED");
                    httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }

            }

        }
        catch(Exception e) {
            System.out.println("INTERNAL SERVER ERROR WHILE ADDING THE ARTIST");
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String nickname = request.getParameter("nickname");
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try{
            if(nickname == null)
            {
                String artists = artistInterface.listArtists();
                String s ="";

                if (!artists.contains(s))
                {
                    httpResponse.setStatus(HttpServletResponse.SC_OK);
                    String artistList = artistInterface.getArtistDetails(nickname);
                    System.out.println(artistList);
                }
                else
                {
                    httpResponse.setStatus(HttpServletResponse.SC_CONTINUE);
                    System.out.println("NO ARTIST");
                }
                System.out.println();
            }
            else
            {
                String artist = artistInterface.getArtistDetails(nickname);
                if (artist !=null) { //artist nickame found
                    httpResponse.setStatus(HttpServletResponse.SC_OK);
                    System.out.println("Artist's nickame found!!");
                    System.out.println(artist.toString());
                }
                else
                {   //No artist with that nickname
                    httpResponse.setStatus(HttpServletResponse.SC_OK);
                    System.out.println("No artist with " + nickname +" as nickname");
                }
            }

            System.out.println();
        }
        catch(Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.out.println("ERROR during the fetch of the list of albums");
            System.out.println();
        }
    }

    //NOT SURE
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String nickname = request.getParameter("nickname");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String biography = request.getParameter("bio");


        try{

            if(!artistInterface.updateArtist(nickname,firstName,lastName,biography))
            {
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                System.out.println("Artist: NOT UPDATED. TRY AGARIN!!");
            }
            else
            {
                httpResponse.setStatus(HttpServletResponse.SC_OK);
                System.out.println("Artist: UPDATED!!");
            }
            System.out.println();
        }
        catch(Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.out.println("INTERNAL SERVER ERROR WHILE UPDATING THE ARTIST");
            System.out.println();
        }
    }


    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String nickname = request.getParameter("nickname");

        try{

            if(!artistInterface.deleteArtist(nickname))
            {
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                System.out.println("Artist: NOT DELETED. TRY AGARIN!!");
            }
            else
            {
                httpResponse.setStatus(HttpServletResponse.SC_OK);
                System.out.println("Artist: DELETED!!");
            }
            System.out.println();
        }
        catch(Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.out.println("INTERNAL SERVER ERROR WHILE DELETING THE ARTIST");
            System.out.println();
        }
    }

}