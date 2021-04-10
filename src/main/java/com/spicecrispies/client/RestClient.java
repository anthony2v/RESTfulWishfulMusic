package com.spicecrispies.client;

import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class RestClient {
    /**
     * Shows the list of albums by sorted by ISRC and title
     * @return list of albums
     */
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
    public String getAlbumInfo(String isrc) {
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
    public String createAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName) {
        //public String createAlbum(String isrc, String title, String description, int releaseYear, String artist) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://localhost:8080/RESTfulMusic/album");
            JSONObject jsonPostData = new JSONObject();
            jsonPostData.put("isrc", isrc);
            jsonPostData.put("title", title);
            jsonPostData.put("description", description);
            jsonPostData.put("releaseYear", "" + releaseYear);
            jsonPostData.put("artistFirstName", artistFirstName);
            jsonPostData.put("artistLastName", artistLastName);
            StringEntity entity = new StringEntity(jsonPostData.toJSONString());
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            CloseableHttpResponse httpResponse = client.execute(httpPost);
            return readResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to create album";
        }
    }

    public String updateAlbum(String isrc, String title, String description, int releaseYear, String artistFirstName, String artistLastName) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut("http://localhost:8080/RESTfulMusic/album");
            JSONObject jsonPutData = new JSONObject();
            jsonPutData.put("isrc", isrc);
            jsonPutData.put("title", title);
            jsonPutData.put("description", description);
            jsonPutData.put("releaseYear", "" + releaseYear);
            jsonPutData.put("artistFirstName", artistFirstName);
            jsonPutData.put("artistLastName", artistLastName);
            StringEntity entity = new StringEntity(jsonPutData.toJSONString());
            entity.setContentType("application/json");
            httpPut.setEntity(entity);
            CloseableHttpResponse httpResponse = client.execute(httpPut);
            return readResponse(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to update album";
        }
    }

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