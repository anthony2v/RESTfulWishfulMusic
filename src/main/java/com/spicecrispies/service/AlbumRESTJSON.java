package com.spicecrispies.service;

import com.spicecrispies.core.entities.AlbumCover;
import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.interfaces.AlbumInterface;
import com.spicecrispies.persistence.AlbumMapper;
import com.spicecrispies.repository.AlbumFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

@Path("album")
public class AlbumRESTJSON implements AlbumInterface {
    private static final AlbumInterface albumImplementation = AlbumFactory.getInstance();
    private static AlbumMapper logData;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listAlbum() {
        try {
            logData.selectAll();
            return albumImplementation.listAlbums();
        } catch (Exception e) {
            System.out.println("Exception caught :" + e.getMessage());
            return "Error listing albums.";
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{isrc}")
    public String getAlbum(@PathParam("isrc") String isrc) {
        try {

            String album = albumImplementation.getAlbumInfo(isrc);

            if (album.equals("")) { // No such album
                return "No album with an ISRC of " + isrc;
            }
            else
            {

            }

            Album album = albumManager.getAlbum(isrc);
            return album;
        } catch(Exception e) {
            System.out.println("Exception caught :" + e.getMessage());
            return "Error getting album.";
        }
    }



    //TODO: Update end point to upload image so it can be stored in AlbumCover

    @POST
    @Path("{isrc}/{title}/{description}/{releaseYear}/{artistFirstName}/{artistLastName}/{albumCover}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addAlbum(@PathParam("isrc") String isrc, @PathParam("title") String title, @PathParam("description")  String description
            , @PathParam("releaseYear") int releaseYear, @PathParam("artistFirstName") String artistFirstName, @PathParam("artistLastName") String artistLastName, @PathParam("albumCover") String albumCover) {
        try {

//            boolean success = albumManager.addAlbum(album);
//
//            if(!success) {
//                return Response.status(Response.Status.FORBIDDEN)
//                        .entity(String.format("Failed to add album"))
//                        .build();
//            }

            //logManager.addLog(new Log(LocalDateTime.now(), Log.ChangeType.ADD, album.getIsrc()));
            return albumImplementation.createAlbum(isrc, title, description, releaseYear, artistFirstName, artistLastName, new AlbumCover(null,null));
        } catch(Exception e) {
            System.out.println("Exception caught :" + e);
            return "Error adding album.";
        }
    }

    @PUT
    @Path("{isrc}/{title}/{description}/{releaseYear}/{artistFirstName}/{artistLastName}/{albumCover}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateAlbum(@PathParam("isrc") String isrc, @PathParam("title") String title, @PathParam("description")  String description
            , @PathParam("releaseYear") int releaseYear, @PathParam("artistFirstName") String artistFirstName, @PathParam("artistLastName") String artistLastName, @PathParam("albumCover") String albumCover) {
        try {

//            boolean success = albumManager.updateAlbum(album);
//
//            if (!success) {
//                return Response.status(Response.Status.FORBIDDEN)
//                        .entity("Failed to update album: no album with an ISRC of " + album.getIsrc())
//                        .build();
//            }
//
//            logManager.addLog(new Log(LocalDateTime.now(), Log.ChangeType.UPDATE, album.getIsrc()));



            return albumImplementation.updateAlbum(isrc, title, description, releaseYear, artistFirstName, artistLastName, new AlbumCover(null,null));
        } catch (Exception e) {
            System.out.println("Exception caught :" + e);
            return "Error updating album.";
        }
    }

    @Override
    public String createAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName, AlbumCover albumCover) throws RepException {
        return null;
    }

    @Override
    public String updateAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName, AlbumCover albumCover) throws RepException {
        return null;
    }

    @DELETE
    @Path("{isrc}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteAlbum(@PathParam("isrc") String isrc) {
        try {
            logManager.addLog(new Log(LocalDateTime.now(), Log.ChangeType.DELETE, isrc));
            return albumImplementation.deleteAlbum(isrc);
        } catch (Exception e) {
            System.out.println("Exception caught :" + e);
            return "Error deleting album.";
        }
    }

    @Override
    public String getAlbumInfo(String isrc) throws RepException {
        return null;
    }

    @Override
    public String listAlbums() throws RepException {
        return null;
    }

    @Override
    public String updateAlbumCoverImage(String isrc, AlbumCover albumCover) throws RepException {
        return null;
    }

    @Override
    public String deleteAlbumCoverImage(String isrc) throws RepException {
        return null;
    }

    @GET
    @Path("{isrc}")
    public AlbumCover getAlbumCoverImage(String isrc) throws RepException {
        AlbumCover coverImage = coverImageManager.getCoverImageByAlbumIsrc(isrc);
        int blobLength = (int) coverImage.getBlob().length();
        byte[] blobAsBytes = coverImage.getBlob().getBytes(1, blobLength);

        return null;
    }

    @GET
    @Path("{isrc}")
    public Response getCoverImage(@PathParam("isrc") String isrc) {
        String errorMessage;
        try{


            return Response.ok(new StreamingOutput() {
                public void write(OutputStream output) throws IOException, WebApplicationException {
                    output.write(blobAsBytes);
                }}).header("Content-Type", coverImage.getMimeType()).build();

        }catch (SQLException ex){
            errorMessage = "There was an error getting the cover image from the server database.";
        }

    }
