package com.spicecrispies.service;

import com.spicecrispies.repository.AlbumFactory;
import com.spicecrispies.interfaces.AlbumInterface;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("album")
public class AlbumREST {
    private static final AlbumInterface albumImplementation = AlbumFactory.getInstance();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String listAlbum() {
        try {
            return albumImplementation.listAlbums();
        } catch (Exception e) {
            System.out.println("Exception caught :" + e.getMessage());
            return "Error listing albums.";
        }
    }

    @GET
    @Path("{isrc}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAlbum(@PathParam("isrc") String isrc) {
        try {
            String album = albumImplementation.getAlbumDetails(isrc);
            if (album.equals("")) { // No such album
                return "No album with an ISRC of " + isrc;
            }
            return album;
        } catch(Exception e) {
            System.out.println("Exception caught :" + e.getMessage());
            return "Error getting album.";
        }
    }

    @POST
    @Path("{isrc}/{title}/{description}/{releaseYear}/{artist}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addAlbum(@PathParam("isrc") String isrc, @PathParam("title") String title, @PathParam("description")  String description
            , @PathParam("releaseYear") int releaseYear, @PathParam("artist") String artist) {
        try {
            return albumImplementation.addAlbum(isrc, title, description, releaseYear, artist);
        } catch(Exception e) {
            System.out.println("Exception caught :" + e);
            return "Error adding album.";
        }
    }

    @PUT
    @Path("{isrc}/{title}/{description}/{releaseYear}/{artist}")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateAlbum(@PathParam("isrc") String isrc, @PathParam("title") String title, @PathParam("description")  String description
            , @PathParam("releaseYear") int releaseYear, @PathParam("artist") String artist) {
        try {
            return albumImplementation.updateAlbum(isrc, title, description, releaseYear, artist);
        } catch (Exception e) {
            System.out.println("Exception caught :" + e);
            return "Error updating album.";
        }
    }

    @DELETE
    @Path("{isrc}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteAlbum(@PathParam("isrc") String isrc) {
        try {
            return albumImplementation.deleteAlbum(isrc);
        } catch (Exception e) {
            System.out.println("Exception caught :" + e);
            return "Error deleting album.";
        }
    }
}
