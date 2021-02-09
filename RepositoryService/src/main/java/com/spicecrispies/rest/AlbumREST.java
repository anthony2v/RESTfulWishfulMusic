package com.spicecrispies.rest;

import com.spicecrispies.repobusiness.AlbumFactory;
import com.spicecrispies.interfaces.AlbumInterface;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("album")
public class AlbumREST {
    private static final AlbumInterface albumImplementation = AlbumFactory.getInstance();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String listAlbum() {
        return albumImplementation.listAlbums();
    }

    @GET
    @Path("{isrc}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAlbum(@PathParam("isrc") String isrc) {
        try {
            String album = albumImplementation.getAlbumDetails(isrc);
            if (album == null) { // No such album
                return "No album with an ISRC of " + isrc;
            }
            return "Album details:" + album;
        }
        catch(Exception e) {
            return "An error occurred while trying to get the album";
        }
    }

    @POST
    @Path("{isrc}/{title}/{description}/{releaseYear}/{artist}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addAlbum(@PathParam("isrc") String isrc, @PathParam("title") String title, @PathParam("description")  String description
            , @PathParam("releaseYear") int releaseYear, @PathParam("artist") String artist) {
        return albumImplementation.addAlbum(isrc, title, description, releaseYear, artist);
    }

    @PUT
    @Path("{isrc}/{title}/{description}/{releaseYear}/{artist}")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateAlbum(@PathParam("isrc") String isrc, @PathParam("title") String title, @PathParam("description")  String description
            , @PathParam("releaseYear") int releaseYear, @PathParam("artist") String artist) {
        return albumImplementation.updateAlbum(isrc, title, description, releaseYear, artist);
    }

    @DELETE
    @Path("{isrc}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteAlbum(@PathParam("isrc") String isrc) {
        return albumImplementation.deleteAlbum(isrc);
    }
}
