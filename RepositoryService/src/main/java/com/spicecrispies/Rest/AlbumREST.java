package com.spicecrispies.Rest;

import com.spicecrispies.Repo_Business.AlbumFactory;
import com.spicecrispies.Repo_Core.Album;
import com.spicecrispies.Repo_Core.AlbumInterface;
import com.spicecrispies.Repo_Core.Artist;
import com.spicecrispies.Repo_Business.ArtistFactory;
import com.spicecrispies.Repo_Implementation.AlbumImplementation;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.stream.Collectors;


@Path("album")
public class AlbumREST {

    private static ArrayList<Album> albums = new ArrayList<>();

    private AlbumInterface albumImplementation = AlbumFactory.getInstance();


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{isrc}")
    public String listAlbum(@PathParam("isrc") String isrc) {
        if (albums.size() == 0) // No albums
            return "There are no albums";
        Album album= albums.stream().filter(album1 -> album1.getIsrc() == isrc).findFirst().orElse(null);
        if (album != null) {
            return album.toString();
        } else {
            return "Album not found!";
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{isrc}")
    public String getAlbum(@PathParam("isrc") String isrc) {
        try {
            String album = albumImplementation.getAlbumDetails(isrc);
            if (album == null) { // No such album
                return "No album with an ISRC of " + isrc;
            }
            return album.toString();
        }
        catch(Exception e) {
            return "An error occurred while trying to get the album";

        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String addAlbum(@FormParam("isrc") String isrc, @FormParam("title") String title, @FormParam("description")  String description
            , @FormParam("releaseYear") int releaseYear) {
        try {

            if (albumImplementation.updateAlbum(isrc, title, description, releaseYear, null)){
                return "Album with isrc " + isrc +": ADDED";
            }
            else {
                return "Album with isrc " + isrc +": FAILED TO ADD";
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

