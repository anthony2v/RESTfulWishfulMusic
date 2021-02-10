package com.spicecrispies;

import com.spicecrispies.interfaces.ArtistInterface;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Scanner;

/**
 * This WebClient class is used to perform API calls to the Artists service, which is implemented using servlet
 * technology. An HTTP client will be used to communicate with the service.
 */

public class WebClient implements ArtistInterface {
    public WebClient() {
    }

    public String listArtists() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://localhost:8080/ArtistServlet");
            CloseableHttpResponse httpResponse = client.execute(httpGet);
            return readResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error occurred when listing artists.";
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

    /**
     * Converts the given response into a string
     * @param response http response
     * @return response string
     * @throws IOException thrown by close method
     */
    public static String readResponse(CloseableHttpResponse response) throws IOException {
        // Handling the IO Stream from the response using scanner
        Scanner sc = new Scanner(response.getEntity().getContent());
        StringBuilder stringResponse = new StringBuilder();
        while (sc.hasNext()) {
            stringResponse.append(sc.nextLine());
            stringResponse.append("\n");
        }
        response.close();
        return stringResponse.toString();
    }
}
