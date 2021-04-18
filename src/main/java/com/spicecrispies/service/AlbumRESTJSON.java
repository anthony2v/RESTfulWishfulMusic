package com.spicecrispies.service;

import com.spicecrispies.core.entities.Album;
import com.spicecrispies.core.enums.QueryType;
import com.spicecrispies.repository.AlbumRepoFactory;
import com.spicecrispies.repository.AlbumRepoImplementation;
import com.spicecrispies.repository.LogManagerFactory;
import com.spicecrispies.repository.LogManagerImplementation;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Path("album")
public class AlbumRESTJSON {
    private static final AlbumRepoImplementation albumManager = (AlbumRepoImplementation)AlbumRepoFactory.getInstance();
    private static final LogManagerImplementation logManager = (LogManagerImplementation)LogManagerFactory.getInstance();

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
    public Response deleteAlbum(@PathParam("id") String id) {
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
}