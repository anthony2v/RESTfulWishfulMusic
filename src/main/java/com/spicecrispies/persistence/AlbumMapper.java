package com.spicecrispies.persistence;

import com.spicecrispies.core.entities.Album;

import java.sql.*;
import java.util.ArrayList;

public class AlbumMapper extends DataMapper {

    public static Album select(String id) throws ClassNotFoundException, SQLException {
        Album album = null;
        Connection connection;
        PreparedStatement statement;
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.prepareStatement("SELECT * FROM album WHERE id = ?");
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            album = new Album(
                    id,
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
                    resultSet.getString("id"),
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
        statement = connection.prepareStatement("INSERT INTO album(id, title, year, artist) VALUES (?,?,?,?)");
        statement.setString(1, album.getId());
        statement.setString(2, album.getTitle());
        statement.setInt(3, album.getReleaseYear());
        statement.setString(4, album.getArtist());
        statement.execute();
        statement.close();
        connection.close();
    }

    public static void delete(String id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.prepareStatement("DELETE FROM album WHERE id = ?");
        statement.setString(1, id);
        statement.execute();
        statement.close();
        connection.close();
    }
}
