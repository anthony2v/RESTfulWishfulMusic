package com.spicecrispies.persistence;

import com.spicecrispies.core.entities.Album;

import java.sql.*;

public class AlbumMapper {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    public Album get(String isrc) {
        Album album = null;
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
            String sql = "SELECT FROM album WHERE isrc = " + isrc;
            ResultSet resultSet = statement.executeQuery(sql);
            album = new Album(
                    isrc,
                    resultSet.getString("title"),
                    resultSet.getString("content_description"),
                    resultSet.getInt("year"),
                    resultSet.getString("artist_first_name"),
                    resultSet.getString("artist_last_name"),
                    new AlbumCover(
                            resultSet.getBlob("cover_image"),
                            resultSet.getString("cover_image_type")
                    )
            );
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("AN ERROR OCCURRED REGISTERING JDBC DRIVER");
            cnfe.printStackTrace();
        } catch (SQLException se) {
            System.out.println("AN ERROR OCCURRED DURING SELECT QUERY EXECUTION");
            se.printStackTrace();
        }
        finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
        return Album;
    }
    public void insert(Album album) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
            String sql = "INSERT INTO album(isrc, title, content_description, year, artist_first_name, artist_last_name, cover_image, cover_image_type) VALUES (" +
                        album.getIsrc(),album.getTitle(), album.getDescription(), album.getReleaseYear(), album.getArtistFirstName(), album.getArtistLastName(),
                        album.getAlbumCover().getAlbumCoverImage(), album.getAlbumCover().getMimeType() + ")";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("AN ERROR OCCURRED REGISTERING JDBC DRIVER");
            cnfe.printStackTrace();
        } catch (SQLException se) {
            System.out.println("AN ERROR OCCURRED DURING INSERT QUERY EXECUTION");
            se.printStackTrace();
        }
        finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
    }
    public void update(Album album) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
            String sql = "UPDATE album SET title = album.getTitle(), content_description = album.getDescription(), year = album.getReleaseYear(), " +
                    "artist_first_name = album.getArtistFirstName(), artist_last_name = album.getArtistLastName(), cover_image = album.getAlbumCover().getAlbumCoverImage(), " +
                    "cover_image_type = album.getAlbumCover().getMimeType() WHERE isrc = " + album.getIsrc() + ")";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("AN ERROR OCCURRED REGISTERING JDBC DRIVER");
            cnfe.printStackTrace();
        } catch (SQLException se) {
            System.out.println("AN ERROR OCCURRED DURING UPDATE QUERY EXECUTION");
            se.printStackTrace();
        }
        finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
    }
    public void delete(String isrc) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
            String sql = "DELETE FROM album WHERE isrc = " + isrc;
            statement.close();
            connection.close();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("AN ERROR OCCURRED REGISTERING JDBC DRIVER");
            cnfe.printStackTrace();
        } catch (SQLException se) {
            System.out.println("AN ERROR OCCURRED DURING DELETE QUERY EXECUTION");
            se.printStackTrace();
        }
        finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
    }
}
