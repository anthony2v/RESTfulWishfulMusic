package com.spicecrispies.Rest;

//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.stream.Collectors;

//@Path("album")
public class AlbumController {

    //private static ArrayList<Album> albums = new ArrayList<>();

/*
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{isrc}")
    public String getAlbum(@PathParam("isrc") String isrc) {
        return isrc + "";
    }


     @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{isrc}")
    public String getAlbumList(@PathParam("irsc") int irsc) {
       Book book = books.stream().filter(album1 -> album1.getisrc() == isrc)
                .findFirst()
                .orElse(null);
        if (book != null) {
            return album.toString();
        } else {
            return "Album not found!";
        }
    }
*/


   // @PUT
    //@Path("{isrc}")
    public void addAlbum() {

        /*
        album = album.stream().filter(album -> !book.getTitle().equals(title))
                .collect(Collectors.toCollection(ArrayList::new));
        Book newAlbum = new Album(isrc, title, description, year);
        albums.add(newAlbum); }






    @PUT
    @Path("{irsc}/{title}/{description}/{year}/{Artist}")
    public void UpdateAlbum(@PathParam("irsc") int irsc, @PathParam("title") String title, @PathParam("description") String description,
                               @PathParam("artist"") Artist artist) {
        //deleteBook(id);
        /createBook(title, author,isbn);
    }


    //@DELETE
    //@Path("{irsc}")
    public void deleteAlbum(@PathParam("irsc") int irsc) {
        albums = albums.stream().filter(album -> album.getirsc() != irsc)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    private void printAlbums() {
        for(Album album: albums) {
            System.out.println(album);
        }
    }
 */




}}