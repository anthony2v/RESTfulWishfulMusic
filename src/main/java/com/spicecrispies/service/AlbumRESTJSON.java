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
import java.util.ArrayList;


@Path("album")
public class AlbumRESTJSON {
    private static final AlbumRepoImplementation albumManager = (AlbumRepoImplementation)AlbumFactory.getInstance();
    private static final LogManagerImplementation logManager = (LogManagerImplementation)LogManagerFactory.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Album createAlbum(Album album){
        try {
            if (albumManager.getAlbumInfo(album.getIsrc()) != null) {
                return new Album("", "ERROR", "Album already in database", -1, "", "");
            }
            albumManager.createAlbum(album);
            logManager.addLog(new LogEntry(LocalDateTime.now(), ChangeType.CREATE, album.getIsrc()));
            return album;
        } catch(RepException re) {
            return new Album("", "ERROR", re.getMessage(), -1, "", "");
        } catch(Exception e) {
            return new Album("", "ERROR", "Error occurred while creating album", -1, "", "");
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Album updateAlbum(Album album) throws RepException {
        try {
            if (albumManager.getAlbumInfo(album.getIsrc()) == null) {
                return new Album("", "ERROR", "Album not in database", -1, "", "");
            }
            albumManager.updateAlbum(album);
            logManager.addLog(new LogEntry(LocalDateTime.now(), ChangeType.UPDATE, album.getIsrc()));
            return album;
        } catch(RepException re) {
            return new Album("", "ERROR", re.getMessage(), -1, "", "");
        } catch(Exception e) {
            return new Album("", "ERROR", "An error occurred when trying to update the album", -1, "", "");
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{isrc}")
    public Album deleteAlbum(@PathParam("isrc") String isrc) {
        try {
            albumManager.deleteAlbum(isrc);
            logManager.addLog(new LogEntry(LocalDateTime.now(), ChangeType.DELETE, isrc));
            return new Album("", "STATUS", "Album Deleted", 1, "", "");
        } catch (RepException re) {
            return new Album("", "ERROR", re.getMessage(), -1, "", "");
        } catch (Exception e) {
            return new Album("", "ERROR", "An error occurred when trying to delete the album", -1, "", "");
        }
    }

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Album> listAlbums() {
        try {
            return albumManager.listAlbums();
        } catch(Exception e) {
            ArrayList<Album> error = new ArrayList<>();
            error.add(new Album("", "ERROR", "An error occurred while trying to get the album list", -1, "", ""));
            return error;
        }
    }

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