package com.spicecrispies.persistence;

import com.spicecrispies.core.entities.Album;

import java.sql.*;
import java.util.ArrayList;

public class AlbumMapper {
    private static final String DRIVER = AlbumDBConfig.DRIVER;
    private static final String URL = AlbumDBConfig.URL;
    private static final String USERNAME = AlbumDBConfig.USERNAME;
    private static final String PASSWORD = AlbumDBConfig.PASSWORD;

    public static Album select(String isrc) throws ClassNotFoundException, SQLException {
        Album album = null;
        Connection connection;
        PreparedStatement statement;
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.prepareStatement("SELECT * FROM album WHERE isrc = ?");
        statement.setString(1, isrc);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            album = new Album(
                    isrc,
                    resultSet.getString("title"),
                    resultSet.getInt("year"),
                    resultSet.getString("artist")
            );
        }
        resultSet.close();
        statement.close();
        connection.close();
        return album;
    }

    public static ArrayList<Album> selectAll() throws ClassNotFoundException, SQLException {
        ArrayList<Album> albums = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.createStatement();
        String sql = "SELECT * FROM album";
        ResultSet resultSet = statement.executeQuery(sql);
        Album album;
        while (resultSet.next()) {
            album = new Album(
                    resultSet.getString("isrc"),
                    resultSet.getString("title"),
                    resultSet.getInt("year"),
                    resultSet.getString("artist")
            );
            albums.add(album);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return albums;
    }

    public static void insert(Album album) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.prepareStatement("INSERT INTO album(isrc, title, year, artist) VALUES (?,?,?,?)");
        statement.setString(1, album.getIsrc());
        statement.setString(2, album.getTitle());
        statement.setInt(3, album.getReleaseYear());
        statement.setString(4, album.getArtist());
        statement.execute();
        statement.close();
        connection.close();
    }

    public static void delete(String isrc) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.prepareStatement("DELETE FROM album WHERE isrc = ?");
        statement.setString(1, isrc);
        statement.execute();
        statement.close();
        connection.close();
    }
}
