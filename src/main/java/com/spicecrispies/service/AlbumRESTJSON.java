package com.spicecrispies.service;

import com.spicecrispies.core.entities.Album;
import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.logging.LogEntry;
import com.spicecrispies.repository.AlbumFactory;
import com.spicecrispies.repository.AlbumRepoImplementation;
import com.spicecrispies.repository.LogManagerFactory;
import com.spicecrispies.repository.LogManagerImplementation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;


@Path("album")
public class AlbumRESTJSON {
    private static final AlbumRepoImplementation albumManager = (AlbumRepoImplementation)AlbumFactory.getInstance();
    private static final LogManagerImplementation logManager = (LogManagerImplementation)LogManagerFactory.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createAlbum(Album album){
        try {
            System.out.println("CREATING ALBUM");
            albumManager.createAlbum(album.getIsrc(), album.getTitle(), album.getDescription(), album.getReleaseYear(), album.getArtistFirstName(), album.getArtistLastName());
            System.out.println("CREATED ALBUM");
            if (albumManager.getAlbumInfo(album.getIsrc()) != null)
            {
                return "Failed to create album";
            }
            System.out.println("LOGGING");
            logManager.addLog(new LogEntry(LocalDateTime.now(), ChangeType.CREATE, album.getIsrc()));

            return "CREATING ALBUM SUCCESS!";
        } catch(RepException re) {
            return re.getMessage();
        } catch(Exception e) {
            return "ERROR in creating album";
        }
    }


//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public String updateAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName) throws RepException {
//        try {
//
//            albumManager.updateAlbum(isrc, title, description, releaseYear, artistFirstName, artistLastName);
//
//            logManager.addLog(new LogEntry(LocalDateTime.now(), ChangeType.UPDATE, isrc));
//
//            return "UPDATE SUCCESS!!";
//        } catch(RepException re) {
//            return re.getMessage();
//        } catch(Exception e) {
//            return "ERROR in updating the album!";
//        }
//    }
//
//    @DELETE
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("{isrc}")
//    public String deleteAlbum(@PathParam("isrc") String isrc) {
//        try {
//            albumManager.deleteAlbum(isrc);
//
//            logManager.addLog(new LogEntry(LocalDateTime.now(), ChangeType.DELETE, isrc));
//
//            return "Album Deleted";
//        } catch (RepException re) {
//            return re.getMessage();
//
//        } catch (Exception e) {
//            return "ERROR in trying to delete the album";
//        }
//    }
//
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{isrc}")
    public Album getAlbumInfo(@PathParam("isrc") String isrc) {
        try {
            Album album = albumManager.getAlbumInfo(isrc);
            if (album == null){
                return new Album("", "", "No album by that ISRC found", -1, "", "");
            } else {
                return album;
            }
        } catch(RepException re) {
            return new Album("", "ERROR", "An error occurred while trying to get the album", -1, "", "");
        }
    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String listAlbums() {
//        try {
//            System.out.println(albumManager.listAlbums());
//            return "LISTING CORRECT";
//        } catch(Exception e) {
//            return "ERROR in list of albums";
//        }
//    }

//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getChangeLogs(LocalDate fromDate, LocalDate toDate, ChangeType changeType) throws RepException {
//        System.out.println("Change Logs");
//        return  getChangeLogs(fromDate,toDate, changeType);
//    }
//
//
//
//    @Override
//    public void clearLogs() throws RepException {
//        try {
//            logManager.clearLogs();
//            System.out.println("Logs cleared");
//        } catch(com.spicecrispies.core.exceptions.RepException e) {
//            throw new RepException(e.getMessage());
//        }
//    }
}