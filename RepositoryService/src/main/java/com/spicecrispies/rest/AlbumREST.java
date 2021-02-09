package com.spicecrispies.rest;

import com.spicecrispies.repobusiness.AlbumFactory;
import com.spicecrispies.entities.Album;
import com.spicecrispies.interfaces.AlbumInterface;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


@Path("album")
public class AlbumREST {

    private static final AlbumInterface albumImplementation = AlbumFactory.getInstance();


    @GET
    @Path("{isrc}/{title}")
    @Produces(MediaType.TEXT_PLAIN)
    public String listAlbum(@PathParam("isrc") String isrc) {
        if (albumImplementation.albums.size() == 0) // No albums
            return "There are no albums";
        Album album= albumImplementation.albums.stream().filter(album1 -> album1.getIsrc() == isrc).findFirst().orElse(null);
        if (album != null) {
            return album.toString();
        } else {
            return "Album not found!";
        }
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
    @Consumes("application/xml")
    public String addAlbum(Album album) {
        try {
            if (albumImplementation.addAlbum(album.getIsrc(), album.getTitle(), album.getDescription(), album.getReleaseYear(), album.getArtist())){
                return "Album with isrc " + album.getIsrc() +": ADDED";
            }
            else {
                return "Album with isrc " + album.getIsrc() +": FAILED TO ADD";
            }
        }
        catch(Exception e) {
            return "ERROR IN ADDING";
        }
    }


    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateAlbum(@FormParam("isrc") String isrc, @FormParam("title") String title, @FormParam("description")  String description
            , @FormParam("releaseYear") int releaseYear) {
        try {

            if (albumImplementation.updateAlbum(isrc, title, description, releaseYear, null)){
                return "Album with isrc " + isrc +": UPDATED";
            }
            else {
                return "Album with isrc " + isrc +": FAILED TO UPDATE";
            }
        }
        catch(Exception e) {
            return "ERROR IN UPDATING";
        }
    }


    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{isrc}")
    public String deleteAlbum(@PathParam("isrc") String isrc) {
        try {

            if (albumImplementation.deleteAlbum(isrc)) {
                return "Album with isrc " + isrc +": DELETED";
            } else {
                return "Album with isrc " + isrc +": FAILED TO DELETE";
            }
        }
        catch(Exception e) {
            return "ERROR IN DELETION";
        }
    }
}

