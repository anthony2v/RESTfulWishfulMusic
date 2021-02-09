package com.spicecrispies;


import com.spicecrispies.interfaces.AlbumInterface;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.util.Scanner;

public class RestClient implements AlbumInterface {
    public RestClient() {

    }

    /**
     * Shows the list of albums by sorted by ISRC and title
     * @return list of albums
     */
    @Override
    public String listAlbums() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:8080/RESTfulMusic/album");
            CloseableHttpResponse httpResponse = client.execute(httpGet);
            return readResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to get album list";
        }
    }

    /**
     * Returns the full album information by ISRC
     * @return full album info
     */
    @Override
    public String getAlbumDetails(String isrc) {
        return null;
    }

    /**
     * Adds a new album to the collection with no artist details
     * @return status of the addition
     */
    @Override
    public String addAlbum(String isrc, String title, String description, int releaseYear, String artist) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(String.format("http://localhost:8080/RESTfulMusic/album/%s/%s/%s/%d/%s",
                    isrc, title, description, releaseYear, artist));
            CloseableHttpResponse httpResponse = client.execute(httpPost);
            return readResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to add customer";
        }
    }

    @Override
    public String updateAlbum(String isrc, String title, String description, int releaseYear, String artist) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(String.format("http://localhost:8080/RESTfulMusic/album/%s/%s/%s/%d/%s",
                    isrc, title, description, releaseYear, artist));
            CloseableHttpResponse httpResponse = client.execute(httpPut);
            return readResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to update customer";
        }
    }

    @Override
    public String deleteAlbum(String isrc) {
        return null;
    }

    /**
     * Reads the response and converts it into a string
     * @param response response from http request
     * @return string of the response
     * @throws IOException
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
