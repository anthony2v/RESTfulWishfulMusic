package servlet;

import com.spicecrispies.repobusiness.ArtistFactory;
import com.spicecrispies.interfaces.ArtistInterface;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "artist")
public class ArtistServlet extends HttpServlet {
    private static final ArtistInterface artistInterface = ArtistFactory.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nickname = request.getParameter("nickname");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String biography = request.getParameter("bio");
        if(biography == null)
            biography = "No biography at this moment";
        // Set response content type
        response.setContentType("text/plain");
        PrintWriter output = response.getWriter();
        try{
            if (nickname == null || firstName == null || lastName == null) {
                output.println("Missing parameters.");
                ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
            else {
               output.println(artistInterface.addArtist(nickname, firstName, lastName, biography));
               ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
            }
        }
        catch(Exception e) {
            System.out.println("INTERNAL SERVER ERROR WHILE ADDING THE ARTIST");
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nickname = request.getParameter("nickname");
        // Set response content type
        response.setContentType("text/plain");
        PrintWriter output = response.getWriter();
        try {
            if(nickname == null) {
                output.println(artistInterface.listArtists());
            } else {
                String artist = artistInterface.getArtistDetails(nickname);
                if (artist.equals(""))
                    output.println("No artist named " + nickname +" found.");
                else
                    output.println(artist);
            }
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
        } catch(Exception e) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            output.println("ERROR during the fetch of the list of albums");
        }
    }

    //NOT SURE
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nickname = request.getParameter("nickname");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String biography = request.getParameter("bio");
        // Set response content type
        response.setContentType("text/plain");
        PrintWriter output = response.getWriter();
        try{
            output.println(artistInterface.updateArtist(nickname,firstName,lastName,biography));
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
        } catch(Exception e) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.out.println("INTERNAL SERVER ERROR WHILE UPDATING THE ARTIST");
        }
    }


    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nickname = request.getParameter("nickname");
        // Set response content type
        response.setContentType("text/plain");
        PrintWriter output = response.getWriter();
        try{
            output.println(artistInterface.deleteArtist(nickname));
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
        }
        catch(Exception e) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            output.println("INTERNAL SERVER ERROR WHILE DELETING THE ARTIST");
        }
    }

}