package com.spicecrispies.service;

import com.spicecrispies.core.entities.AlbumCover;
import com.spicecrispies.core.interfaces.AlbumInterface;
import com.spicecrispies.repository.AlbumFactory;

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
            String album = albumImplementation.getAlbumInfo(isrc);
            if (album.equals("")) { // No such album
                return "No album with an ISRC of " + isrc;
            }
            return album;
        } catch(Exception e) {
            System.out.println("Exception caught :" + e.getMessage());
            return "Error getting album.";
        }
    }

    //TODO: Update end point to upload image so it can be stored in AlbumCover
    @POST
    @Path("{isrc}/{title}/{description}/{releaseYear}/{artistFirstName}/{artistLastName}/{albumCover}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addAlbum(@PathParam("isrc") String isrc, @PathParam("title") String title, @PathParam("description")  String description
            , @PathParam("releaseYear") int releaseYear, @PathParam("artistFirstName") String artistFirstName, @PathParam("artistLastName") String artistLastName, @PathParam("albumCover") String albumCover) {
        try {
            return albumImplementation.createAlbum(isrc, title, description, releaseYear, artistFirstName, artistLastName, new AlbumCover(null,null));
        } catch(Exception e) {
            System.out.println("Exception caught :" + e);
            return "Error adding album.";
        }
    }

    @PUT
    @Path("{isrc}/{title}/{description}/{releaseYear}/{artistFirstName}/{artistLastName}/{albumCover}")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateAlbum(@PathParam("isrc") String isrc, @PathParam("title") String title, @PathParam("description")  String description
            , @PathParam("releaseYear") int releaseYear, @PathParam("artistFirstName") String artistFirstName, @PathParam("artistLastName") String artistLastName, @PathParam("albumCover") String albumCover) {
        try {
            return albumImplementation.updateAlbum(isrc, title, description, releaseYear, artistFirstName, artistLastName, new AlbumCover(null,null));
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
