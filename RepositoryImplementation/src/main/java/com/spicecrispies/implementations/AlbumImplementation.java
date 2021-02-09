package com.spicecrispies.implementations;

import com.spicecrispies.entities.Album;
import com.spicecrispies.interfaces.AlbumInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class AlbumImplementation implements AlbumInterface, Serializable {
    private static final int MAX_AVAILABLE = 1;
    private static final Semaphore sema = new Semaphore(MAX_AVAILABLE, true);
    private static final ArrayList<Album> albums = new ArrayList<>();

    @Override
    public String listAlbums() {

        StringBuilder str = new StringBuilder();
        try {
            sema.acquire();
            for (Album album : albums) {
                str.append(album.toString() + "\n");
            }

            return str.toString();
        } catch (Exception e) {
            System.out.println("Exception caught :" + e);
        } finally {

            sema.release();
            return str.toString();
        }
    }

    @Override
    public String getAlbumDetails(String s) {
        try {
            sema.acquire();
            for (Album album : albums) {
                if (album.getIsrc().equalsIgnoreCase(s)) {
                    return album.toString();
                }
            }
            return "";
        }catch(Exception e){
            System.out.println("Exception caught :" + e);
        }finally{
            sema.release();
        }
        return "";
    }


    @Override
    public boolean addAlbum(String isrc, String title, String description, int releaseYear, String artist){
        try {
            sema.acquire();
            if (getAlbumDetails(isrc).equalsIgnoreCase("")) {
                //add album (adds a new album to the collection; no artist details) thats why i put null
                albums.add(new Album(isrc, title, description, releaseYear, artist));
            }
            return true;
        }catch(Exception e){
            System.out.println("Exception caught :" + e);
        }finally{
            sema.release();
        }
        return false;
    }

    @Override
    public boolean updateAlbum(String isrc, String title, String description, int releaseYear, String artist) {
        boolean flag = false;
        try {
            sema.acquire();

            for (Album album : albums) {
                if (album.getIsrc().equalsIgnoreCase(isrc)) {
                    album.setTitle(title);
                    album.setDescription(description);
                    album.setReleaseYear(releaseYear);
                    album.setArtist(artist);
                    flag=true;
                    return flag;
                }
            }
            flag=false;
            return flag;
        } catch (Exception e) {
            System.out.println("Exception caught :" + e);
        } finally {
            sema.release();
            return flag;
        }
    }

    @Override
    public boolean deleteAlbum(String isrc) {
        boolean flag = false;
        try {
            sema.acquire();
            int index = -1;
            for (
                    Album album : albums) {
                if (album.getIsrc().equalsIgnoreCase(isrc)) {
                    index = albums.indexOf(album);
                }
            }

            if (index != -1) {
                albums.remove(index);
                flag=true;
                return flag;
            }
            flag=false;
            return flag;
        }catch (Exception e) {
            System.out.println("Exception caught :" + e);
        } finally {
            sema.release();
            return flag;
        }
    }
}