package com.spicecrispies.service;

import com.spicecrispies.core.entities.Album;
import com.spicecrispies.core.enums.QueryType;
import com.spicecrispies.persistence.AlbumMapper;
import com.spicecrispies.repository.*;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

import static com.spicecrispies.service.AlbumDataAggregator.searchAlbums;


@Path("/album")
public class AlbumRESTJSON {
    private static final AlbumRepoImplementation albumManager = (AlbumRepoImplementation)AlbumRepoFactory.getInstance();
    private static final LogManagerImplementation logManager = (LogManagerImplementation)LogManagerFactory.getInstance();
    private static final AuthenticationREST authentication =  AuthenticationREST.getInstance();


    private static Album albums = new Album();
    private static AlbumMapper albumMapper = new AlbumMapper();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAlbum(Album album) {
        logManager.addLog(LocalDateTime.now().toString(), QueryType.CREATE, album.getId());
        try {
            if (albumManager.createAlbum(album))
                return Response.status(Response.Status.OK).entity("Album successfully added to wishlist!").build();
            else
                return Response.status(Response.Status.CONFLICT).entity("Album already in wishlist!").build();
        } catch (ClassNotFoundException classNotFoundException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred registering the JDBC driver.").build();
        } catch (InterruptedException interruptedException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An interrupt error occurred when trying to acquire lock.").build();
        } catch (SQLException sqlException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred during select query execution.").build();
        } catch (Exception exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred when trying to create the album.").build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteAlbum(@PathParam("id") String id) { //Remove from wishlist
        logManager.addLog(LocalDateTime.now().toString(), QueryType.DELETE, id);
        try {
            if (albumManager.deleteAlbum(id))
                return Response.status(Response.Status.OK).entity("Album successfully deleted from wishlist!").build();
            else
                return Response.status(Response.Status.CONFLICT).entity("Album not found and deleted, please verify ID!").build();
        } catch (ClassNotFoundException classNotFoundException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred registering the JDBC driver.").build();
        } catch (InterruptedException interruptedException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An interrupt error occurred when trying to acquire lock.").build();
        } catch (SQLException sqlException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred during delete query execution.").build();
        } catch (Exception exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred when trying to delete the album.").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getAlbumInfo(@PathParam("id") String id) {
        logManager.addLog(LocalDateTime.now().toString(), QueryType.SELECT, id);
        try {
            Album album = albumManager.getAlbumInfo(id);
            if (album != null)
                return Response.status(Response.Status.OK).entity(album).build();
            else
                return Response.status(Response.Status.NO_CONTENT).build();
        } catch (ClassNotFoundException classNotFoundException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred registering the JDBC driver.").build();
        } catch (SQLException sqlException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred during select query execution.").build();
        } catch (Exception exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred when trying to get the album.").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAlbums() {
        logManager.addLog(LocalDateTime.now().toString(), QueryType.SELECT, "*");
        try {
            ArrayList<Album> wishlist = albumManager.listAlbums();
            if (wishlist.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).build();
            } else {
                GenericEntity<List<Album>> genericEntity = new GenericEntity<List<Album>>(wishlist) {};
                return Response.status(Response.Status.OK).entity(genericEntity).build();
            }
        } catch (ClassNotFoundException classNotFoundException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred registering the JDBC driver.").build();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred during select query execution.").build();
        } catch (Exception exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred when trying to get the albums.").build();
        }
    }

    @GET
    @Path("logs/{fromDate}/{toDate}/{queryType}")
    public String getChangeLogs(@PathParam("fromDate") String fromDate,@PathParam("toDate") String toDate,@PathParam("queryType") String queryType) {
        System.out.println("Change Logs");
        return logManager.getChangeLogs(fromDate, toDate, queryType);
    }

    public void clearLogs() {
        logManager.clearLogs();
        System.out.println("Logs cleared");
    }


    public ArrayList<Album> getAlbumInfo() throws SQLException, ClassNotFoundException {
        return AlbumMapper.selectAll();
    }

    @Path("search/{album}/{artist}")
    public String search(@PathParam("album") String album, @PathParam("artist") String artist) throws IOException, ParseException {
        System.out.println("Album: " + album + " , Artist: ");
        System.out.println("Result : /n" );
        return searchAlbums(album,artist).toString();
    }

    @Path("{userID}/{albumID}")
    public Response addWishlist(@PathParam("userID") String userID, @PathParam("albumID") String albumID) throws IOException, ParseException, SQLException, ClassNotFoundException {
        if (userID == null && albumID == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        if (userID == null && albumID != null) {
            return Response.status(Response.Status.CONFLICT).entity("Error with USER ID!Please verify USER ID!").build();
        }

        if (userID != null && albumID == null) {
            return Response.status(Response.Status.CONFLICT).entity("Error with ALBUM ID!Please verify ALBUM ID!").build();
        }


        albumMapper.insert(albums);

        return Response.status(Response.Status.OK).entity("ADDED to wishlist!!").build();
    }

    @Path("removeWishlist/{userID}/{albumID}")
    public Response removeWishlist(@PathParam("userID") String userID, @PathParam("albumID") String albumID) throws IOException, ParseException, SQLException, ClassNotFoundException {
        if (userID == null && albumID == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        if (userID == null && albumID != null) {
            return Response.status(Response.Status.CONFLICT).entity("Error with USER ID!Please verify USER ID!").build();
        }

        if (userID != null && albumID == null) {
            return Response.status(Response.Status.CONFLICT).entity("Error with ALBUM ID!Please verify ALBUM ID!").build();
        }

        albumMapper.delete(albumID);

        return Response.status(Response.Status.OK).entity("DELETED from wishlist!!").build();

    }


}