package com.spicecrispies;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * This WebClient class is used to perform API calls to the Artists service, which is implemented using servlet
 * technology. An HTTP client will be used to communicate with the service.
 */

public class WebClient implements Client {

    public WebClient() {
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
        String response = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(String.format("http://localhost:8080/RESTfulMusic/album/%s/%s/%s/%d/%s",
                    isrc, title, description, releaseYear, artist));
            CloseableHttpResponse httpResponse = client.execute(httpPost);
            response = httpResponse.toString();
            httpResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
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
