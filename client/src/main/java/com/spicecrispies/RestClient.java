package com.spicecrispies;

import com.spicecrispies.interfaces.AlbumInterface;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.util.Scanner;

public class RestClient implements AlbumInterface {
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
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(String.format("http://localhost:8080/RESTfulMusic/album/%s", isrc));
            CloseableHttpResponse httpResponse = client.execute(httpGet);
            return readResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to get album details";
        }
    }

    /**
     * Adds a new album to the collection
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
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete(String.format("http://localhost:8080/RESTfulMusic/album/%s", isrc));
            CloseableHttpResponse httpResponse = client.execute(httpDelete);
            return readResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to delete customer";
        }
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
