package com.spicecrispies.service;

import com.spicecrispies.core.entities.Album;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AlbumDataAggregator {
    public static ArrayList<Album> searchAlbums(String album, String artist) throws IOException, ParseException {
        String albumNoSpaces = album.replaceAll("\\p{Space}", "%20");
        String artistNoSpaces = artist.replaceAll("\\p{Space}", "%20");
        CloseableHttpClient client = HttpClients.createDefault();
        String musicBrainzUrl = "https://musicbrainz.org/ws/2/release-group/";
        HttpGet httpGet = new HttpGet(musicBrainzUrl + "?query=work%3A%22" + albumNoSpaces + "%22%20AND%20artist%3A%22" + artistNoSpaces + "%22&fmt=json");
        CloseableHttpResponse httpResponse = client.execute(httpGet);

        // Parse the response string into a json array and then get the album details
        JSONArray responseJSONArray = (JSONArray) readResponse(httpResponse).get("release-groups");
        ArrayList<Album> searchResults = new ArrayList<>();
        for (Object jsonObject1 : responseJSONArray) {
            JSONObject searchResult = (JSONObject) jsonObject1;
            if (searchResult.get("primary-type").equals("Album")) {
                Album albumResult = new Album((String) searchResult.get("id"), (String) searchResult.get("title"), Integer.parseInt(((String) searchResult.get("first-release-date")).split("-")[0]), artist);
                searchResults.add(albumResult);
            }
        }
        return searchResults;
    }

    public static JSONObject readResponse(CloseableHttpResponse response) throws IOException, ParseException {
        // Handling the IO Stream from the response using scanner
        Scanner sc = new Scanner(response.getEntity().getContent());
        StringBuilder stringResponse = new StringBuilder();
        while (sc.hasNext()) {
            stringResponse.append(sc.nextLine());
            stringResponse.append("\n");
        }
        response.close();
        // Converting to a JSON object before returning it
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(stringResponse.toString());
    }
}
