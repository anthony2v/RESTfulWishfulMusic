package com.spicecrispies.Rest;

import com.spicecrispies.Repo_Base.Album;
import com.spicecrispies.Repo_Base.Artist;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.stream.Collectors;


@Path("album")
public class AlbumREST {

    private static ArrayList<Album> albums = new ArrayList<>();


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{isrc}")
    public String getAlbum(@PathParam("isrc") String isrc) {
        return isrc + "";
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{isrc}")
    public String getAlbumList(@PathParam("irsc") String irsc) {
        Album album= albums.stream().filter(album1 -> album1.getIsrc() == irsc)
                .findFirst()
                .orElse(null);
        if (album != null) {
            return album.toString();
        } else {
            return "Album not found!";
        }
    }


    @PUT
    @Path("{isrc}")
    public void addAlbum() {

    }



    @PUT
    @Path("{irsc}/{title}/{description}/{year}/{Artist}")
    public void UpdateAlbum(@PathParam("irsc") int irsc, @PathParam("title") String title, @PathParam("description") String description,
                            @PathParam("artist") Artist artist) {

    }


    @DELETE
    @Path("{irsc}")
    public void deleteAlbum(@PathParam("irsc") String irsc) {
        albums = albums.stream().filter(album -> album.getIsrc() != irsc)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    private void printAlbums() {
        for(Album album: albums) {
            System.out.println(album);
        }
    }

}