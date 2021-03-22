package com.spicecrispies.client;

import com.spicecrispies.core.entities.AlbumCover;
import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.interfaces.AlbumInterface;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.time.LocalDate;
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
    public String getAlbumInfo(String isrc) throws RepException {
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
    public String createAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName, AlbumCover albumCover) throws RepException {
        //public String createAlbum(String isrc, String title, String description, int releaseYear, String artist) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(String.format("http://localhost:8080/RESTfulMusic/album/%s/%s/%s/%d/%s/%s/%s/%s",
                    isrc, title, description, releaseYear, artistFirstName, artistLastName, albumCover));
            CloseableHttpResponse httpResponse = client.execute(httpPost);
            return readResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to add customer";
        }
    }

    @Override
    public String updateAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName, AlbumCover albumCover) throws RepException {
        //public String updateAlbum(String isrc, String title, String description, int releaseYear, String artist) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(String.format("http://localhost:8080/RESTfulMusic/album/%s/%s/%s/%d/%s/%s/%s/%s",
                    isrc, title, description, releaseYear,  artistFirstName, artistLastName, albumCover));
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

    @Override
    public void updateAlbumCoverImage(String isrc, AlbumCover albumCover) throws RepException {

    }

    @Override
    public void deleteAlbumCoverImage(String isrc) throws RepException {

    }

    @Override
    public AlbumCover getAlbumCoverImage(String isrc) throws RepException {
        return null;
    }

    @Override
    public String getChangeLogs(LocalDate fromDate, LocalDate toDate, ChangeType changeType) throws RepException {
        return null;
    }

    @Override
    public void clearLogs() throws RepException {

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