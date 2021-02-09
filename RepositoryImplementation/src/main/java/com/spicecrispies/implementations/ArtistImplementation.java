package com.spicecrispies.implementations;

import com.spicecrispies.entities.Artist;
import com.spicecrispies.interfaces.ArtistInterface;
import java.io.Serializable;
import java.util.concurrent.Semaphore;

public class ArtistImplementation implements ArtistInterface, Serializable {
    private static final int MAX_AVAILABLE = 1;
    private final Semaphore sema = new Semaphore(MAX_AVAILABLE, true);

    @Override
    public String listArtists() {
        StringBuilder str = new StringBuilder();
        try {
            sema.acquire();
            for (Artist artist : artists) {
                str.append(artist.toString() + "\n");
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
    public String getArtistDetails(String s) {
        try {
            sema.acquire();
            for (Artist artist : artists) {
                if (artist.getNickname().equalsIgnoreCase(s)) {
                    return artist.toString();
                }
            }

            return "";
        } catch (Exception e) {
            System.out.println("Exception caught :" + e);
        } finally {
            sema.release();
            return "";
        }
    }

    @Override
    public boolean addArtist(String nickName, String firstName, String lastName, String autoBiography) {
        try {
            sema.acquire();
            if (getArtistDetails(nickName).equalsIgnoreCase("")) {
                artists.add(new Artist(nickName, firstName, lastName, autoBiography));
            }
            return true;
        } catch (Exception e) {
            System.out.println("Exception caught :" + e);
        } finally {
            sema.release();
            return true;
        }
    }

    @Override
    public boolean updateArtist(String nickName, String firstName, String lastName, String biography) {
        boolean flag = false;
        try {
            sema.acquire();

            for (Artist artist : artists) {
                if (artist.getNickname().equalsIgnoreCase(nickName)) {
                    artist.setFirstName(firstName);
                    artist.setLastName(lastName);
                    artist.setBiography(biography);
                    flag = true;
                    return flag;
                }
            }

            flag = false;
            return flag;
        } catch (Exception e) {
            System.out.println("Exception caught :" + e);
        } finally {
            sema.release();
            return flag;
        }
    }

    @Override
    public boolean deleteArtist(String nickName) {
        boolean flag = false;
        try {
            sema.acquire();
            int index = -1;
            for (Artist artist : artists) {
                if (artist.getNickname().equalsIgnoreCase(nickName)) {
                    index = artists.indexOf(artist);
                }
            }

            if (index != -1) {
                artists.remove(index);
                flag = true;
                return flag;
            }
            flag = false;
            return flag;
        } catch (Exception e) {
            System.out.println("Exception caught :" + e);
        } finally {
            sema.release();
            return flag;
        }
    }
}