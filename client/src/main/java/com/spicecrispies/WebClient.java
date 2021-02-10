package com.spicecrispies;

import com.spicecrispies.interfaces.ArtistInterface;
import org.apache.http.client.methods.*;
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
            HttpGet httpGet = new HttpGet("http://localhost:8081/ArtistServlet");
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
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(String.format("http://localhost:8081/ArtistServlet?nickname=%s", nickName));
            CloseableHttpResponse httpResponse = client.execute(httpGet);
            return readResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error occurred when getting artist info.";
    }

    public String addArtist(String nickName, String firstName, String lastName, String autoBiography) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(String.format("http://localhost:8081/ArtistServlet?nickname=%s&firstname=%s&lastname=%s&bio=%s",
                    nickName, firstName, lastName, autoBiography));
            CloseableHttpResponse httpResponse = client.execute(httpPost);
            return readResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error occurred when adding artist.";
    }

    public String updateArtist(String nickName, String firstName, String lastName, String autoBiography) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(String.format("http://localhost:8081/ArtistServlet?nickname=%s&firstname=%s&lastname=%s&bio=%s",
                    nickName, firstName, lastName, autoBiography));
            CloseableHttpResponse httpResponse = client.execute(httpPut);
            return readResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error occurred when updating artist.";
    }

    /**
     * Delete the artist's record
     * @return status of the deletion
     */
    public String deleteArtist(String nickName) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete(String.format("http://localhost:8081/ArtistServlet?nickname=%s", nickName));
            CloseableHttpResponse httpResponse = client.execute(httpDelete);
            return readResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error occurred when deleting artist.";
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
