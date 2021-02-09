package com.spicecrispies;

import com.spicecrispies.interfaces.ArtistInterface;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * This WebClient class is used to perform API calls to the Artists service, which is implemented using servlet
 * technology. An HTTP client will be used to communicate with the service.
 */

public class WebClient implements ArtistInterface {
    public WebClient() {
    }

    public String listArtists() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet();
        return null;
    }

    /**
     * Returns the full artist information including bio.
     * @return full artist information
     */
    public String getArtistDetails(String nickName) {
        return null;
    }

    public String addArtist(String nickName, String firstName, String lastName, String autoBiography) {
        return null;
    }

    public String updateArtist(String nickName, String firstName, String lastName, String autoBiography) {
        return null;
    }

    /**
     * Delete the artist's record
     * @return status of the deletion
     */
    public String deleteArtist(String nickName) {
        return null;
    }
}
