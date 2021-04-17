package com.spicecrispies.persistence;

import com.spicecrispies.core.entities.Album;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserAlbumMapper extends DataMapper{
    public static void insert(String userId, String albumId) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.prepareStatement("INSERT INTO userAlbum(user_id, album_id) VALUES (?,?)");
        statement.setString(1, userId);
        statement.setString(2, albumId);
        statement.execute();
        statement.close();
        connection.close();
    }

    public static void deleteAllAlbumsForUser(String userId) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.prepareStatement("DELETE FROM userAlbum WHERE user_id = ?");
        statement.setString(1, userId);
        statement.execute();
        statement.close();
        connection.close();
    }

    public static void deleteAlbumForUser(String userId, String albumId) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.prepareStatement("DELETE FROM userAlbum WHERE user_id = ? AND album_id = ?");
        statement.setString(1, userId);
        statement.setString(2, albumId);
        statement.execute();
        statement.close();
        connection.close();
    }
}
