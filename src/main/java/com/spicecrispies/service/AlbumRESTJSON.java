package com.spicecrispies.service;

import com.spicecrispies.core.entities.Album;
import com.spicecrispies.core.enums.QueryType;
import com.spicecrispies.core.logging.LogEntry;
import com.spicecrispies.persistence.UserAlbumMapper;
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

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.ParseException;

import static com.spicecrispies.service.AlbumDataAggregator.searchAlbums;


@Path("/album")
public class AlbumRESTJSON {
    private static final AlbumRepoImplementation albumManager = (AlbumRepoImplementation)AlbumRepoFactory.getInstance();
    private static final LogManagerImplementation logManager = (LogManagerImplementation)LogManagerFactory.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAlbum(@HeaderParam("x-api-key") String token, Album album) {
        if (!validateToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        logManager.addLog(LocalDateTime.now().toString(), QueryType.CREATE, album.getId());
        try {
            if (albumManager.createAlbum(album)) {
                return Response.status(Response.Status.OK).build();
            }
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
    public Response deleteAlbum(@HeaderParam("x-api-key") String token, @PathParam("id") String id) { //Remove from wishlist
        if (!validateToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        logManager.addLog(LocalDateTime.now().toString(), QueryType.DELETE, id);
        try {
            if (albumManager.deleteAlbum(id)) {
                return Response.status(Response.Status.OK).entity("Album successfully deleted from wishlist!").build();
            }
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
    @Path("logs/{queryType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChangeLogs(@PathParam("queryType") String queryType) {
        GenericEntity<List<String>> genericEntity = new GenericEntity<List<String>>(logManager.getChangeLogs(queryType)) {};
        return Response.status(Response.Status.OK).entity(genericEntity).build();
    }

    @DELETE
    @Path("logs/clear")
    public void clearLogs() {
        logManager.clearLogs();
        System.out.println("Logs cleared");
    }

    @GET
    @Path("search/{album}/{artist}")
    public Response search(@PathParam("album") String album, @PathParam("artist") String artist) throws IOException, ParseException {
        ArrayList<Album> searchResults = searchAlbums(album,artist);
        GenericEntity<List<Album>> genericEntity = new GenericEntity<List<Album>>(searchResults) {};
        return Response.status(Response.Status.OK).entity(genericEntity).build();
    }

    private boolean validateToken(String token) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(String.format("%suser/authentication", Main.BASE_URI));
            httpPost.addHeader("x-api-key", token);
            CloseableHttpResponse httpResponse = client.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            String isAuthenticated = EntityUtils.toString(entity);
            httpResponse.close();
            if(isAuthenticated.equals("true")){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getUserFromToken(String token) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(String.format("%suser", Main.BASE_URI));
            httpGet.addHeader("x-api-key", token);
            CloseableHttpResponse httpResponse = client.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            String username = EntityUtils.toString(entity);
            httpResponse.close();
            return username;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}