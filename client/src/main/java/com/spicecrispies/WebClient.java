package com.spicecrispies;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * This WebClient class is used to perform API calls to the Artists service, which is implemented using servlet
 * technology. An HTTP client will be used to communicate with the service.
 */

public class WebClient implements Client {
    String URI;

    public WebClient(String URI) {
        this.URI = URI;
    }

    public String listArtists() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(URI);
        return null;
    }

    /**
     * Returns the full artist information including bio.
     * @return full artist information
     */
    public String getArtistDetails() {
        return null;
    }

    public String addArtist() {
        return null;
    }

    public String updateArtist() {
        return null;
    }

    /**
     * Delete the artist's record
     * @return status of the deletion
     */
    public String deleteArtist() {
        return null;
    }

    /**
     * Shows the list of albums by sorted by ISRC and title
     * @return list of albums
     */
    public String listAlbums() {
        return null;
    }

    /**
     * Returns the full album information by ISRC
     * @return full album info
     */
    public String getAlbumDetails() {
        return null;
    }

    /**
     * Adds a new album to the collection with no artist details
     * @return status of the addition
     */
    public String addAlbum() {
        return null;
    }

    public String updateAlbumInfo() {
        return null;
    }

    public String deleteAlbum() {
        return null;
    }
}