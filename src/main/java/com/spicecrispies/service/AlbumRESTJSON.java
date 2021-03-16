package com.spicecrispies.service;

import com.spicecrispies.core.entities.Album;
import com.spicecrispies.core.entities.AlbumCover;
import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.interfaces.AlbumInterface;
import com.spicecrispies.core.interfaces.LogManagerInterface;
import com.spicecrispies.core.logging.LogEntry;
import com.spicecrispies.repository.AlbumFactory;
import com.spicecrispies.repository.AlbumImplementation;
import com.spicecrispies.repository.LogManagerFactory;
import com.spicecrispies.repository.LogManagerImplementation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Path("album")
public class AlbumRESTJSON implements AlbumInterface {
    private AlbumInterface albumManager = (AlbumImplementation) AlbumFactory.getInstance();
    private LogManagerInterface logManager = (LogManagerImplementation) LogManagerFactory.getInstance();
    private AlbumImplementation albumCover;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName, AlbumCover albumCover){
        try {
            albumManager.createAlbum(isrc, title, description, releaseYear, artistFirstName, artistLastName, albumCover);

            if (albumManager.getAlbumInfo(isrc).isEmpty())
            {
                return "Failed to create album";
            }

            logManager.addLog(new LogEntry(LocalDateTime.now(), ChangeType.CREATE, isrc));

            return "CREATING ALBUM SUCCESS!";
        } catch(RepException re) {
            return re.getMessage();
        } catch(Exception e) {
            return "ERROR in creating album";
        }
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName, AlbumCover albumCover) throws RepException {
        try {

            albumManager.updateAlbum(isrc, title, description, releaseYear, artistFirstName, artistLastName, albumCover);

            logManager.addLog(new LogEntry(LocalDateTime.now(), ChangeType.UPDATE, isrc));

            return "UPDATE SUCCESS!!";
        } catch(RepException re) {
            return re.getMessage();
        } catch(Exception e) {
            return "ERROR in updating the album!";
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{isrc}")
    public String deleteAlbum(@PathParam("isrc") String isrc) {
        try {
            albumManager.deleteAlbum(isrc);

            logManager.addLog(new LogEntry(LocalDateTime.now(), ChangeType.DELETE, isrc));

            return "Album Deleted";
        } catch (RepException re) {
            return re.getMessage();

        } catch (Exception e) {
            return "ERROR in trying to delete the album";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{isrc}")
    public String getAlbumInfo(@PathParam("isrc") String isrc) {
        try {
            String album = albumManager.getAlbumInfo(isrc);

            if (album == null){
                return "No album with an ISRC of " + isrc; }
            else
            {
                return "Album Info" + album;
            }

        } catch(Exception e) {
            return "An error occurred while trying to get the album";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listAlbums() {
        try {
            System.out.println(albumManager.listAlbums());
            return "LISTING CORRECT";
        } catch(Exception e) {
            return "ERROR in list of albums";
        }
    }


    @Override
    public void updateAlbumCoverImage(String isrc, AlbumCover albumCover) throws RepException {

        if(albumCover.getAlbumCoverImage() == null ){
            System.out.println("ALBUM COVER IMAGE UPDATED");
        }else{
            System.out.println("UNABLE to update album cover image.");
        }
    }


    @DELETE
    @Path("{isrc}")
    public void deleteAlbumCoverImage(@PathParam("isrc") String isrc) throws RepException{
        try {
            albumCover.deleteAlbumCoverImage(isrc);
            if(albumCover.getAlbumCoverImage(isrc) == null ){
                System.out.println("ALBUM COVER IMAGE DELETED");
            }else{
                System.out.println("UNABLE to delete album cover image.");
            }
        }
        catch(RepException e) {
            System.out.println("ERROR IN DELETING album cover image");
        }
    }

    @Override
    public AlbumCover getAlbumCoverImage(String isrc) throws RepException {
        try {
            return albumCover.getAlbumCoverImage(isrc);
        } catch (com.spicecrispies.core.exceptions.RepException e) {
            throw new RepException(e.getMessage());
        }
    }

    @Override
    public String getChangeLogs(LocalDate fromDate, LocalDate toDate, ChangeType changeType) throws RepException {
        return  getChangeLogs(fromDate,toDate, changeType);
    }



    @Override
    public void clearLogs() throws RepException {
        try {
            logManager.clearLogs();
            System.out.println("Logs cleared");
        } catch(com.spicecrispies.core.exceptions.RepException e) {
            throw new RepException(e.getMessage());
        }
    }
}