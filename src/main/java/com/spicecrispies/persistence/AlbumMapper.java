package com.spicecrispies.persistence;

import com.spicecrispies.core.entities.Album;
import com.spicecrispies.core.entities.AlbumCover;
import com.spicecrispies.core.enums.ChangeType;
import com.spicecrispies.core.exceptions.RepException;
import com.spicecrispies.core.interfaces.LogManagerInterface;
import com.spicecrispies.core.logging.LogEntry;
import com.spicecrispies.repository.LogManagerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlbumMapper {
    private static final String DRIVER = AlbumDBConfig.DRIVER;
    private static final String URL = AlbumDBConfig.URL;
    private static final String USERNAME = AlbumDBConfig.USERNAME;
    private static final String PASSWORD = AlbumDBConfig.PASSWORD;
    private static final LogManagerInterface logManager = LogManagerFactory.getInstance();

    public static Album select(String isrc) {
        Album album = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("SELECT * FROM album WHERE isrc = ?");
            statement.setString(1, isrc);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                album = new Album(
                        isrc,
                        resultSet.getString("title"),
                        resultSet.getString("content_description"),
                        resultSet.getInt("year"),
                        resultSet.getString("artist_first_name"),
                        resultSet.getString("artist_last_name"),
                        new AlbumCover(
                                resultSet.getBytes("cover_image"),
                                resultSet.getString("cover_image_type")
                        )
                );
            }
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
        return album;
    }
    public static List<Album> selectAll() {
        List<Album> albums = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String sql = "SELECT * FROM album";
            ResultSet resultSet = statement.executeQuery(sql);
            Album album = null;
            while (resultSet.next()) {
                album = new Album(
                        resultSet.getString("isrc"),
                        resultSet.getString("title"),
                        resultSet.getString("content_description"),
                        resultSet.getInt("year"),
                        resultSet.getString("artist_first_name"),
                        resultSet.getString("artist_last_name"),
                        new AlbumCover(
                                resultSet.getBytes("cover_image"),
                                resultSet.getString("cover_image_type")
                        )
                );
                albums.add(album);
            }
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
        return albums;
    }
    public static void insert(Album album) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            logManager.addLog(LocalDateTime.now().toString(), ChangeType.CREATE, album.getIsrc());
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("INSERT INTO album(isrc, title, content_description, year, artist_first_name, artist_last_name, cover_image, cover_image_type) VALUES (?,?,?,?,?,?,?,?)");
            statement.setString(1, album.getIsrc());
            statement.setString(2, album.getTitle());
            statement.setString(3, album.getDescription());
            statement.setInt(4, album.getReleaseYear());
            statement.setString(5, album.getArtistFirstName());
            statement.setString(6, album.getArtistLastName());
            statement.setBytes(7, album.getAlbumCover().getAlbumCoverImage());
            statement.setString(8, album.getAlbumCover().getMimeType());
            statement.execute();
            statement.close();
            connection.close();
        } catch (RepException re) {
            System.out.println(re.getMessage());
            re.printStackTrace();
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
    public static void update(Album album) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            logManager.addLog(LocalDateTime.now().toString(), ChangeType.UPDATE, album.getIsrc());
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("UPDATE album SET title = ?, content_description = ?, year = ?, artist_first_name = ?, artist_last_name = ?, cover_image = ?, cover_image_type = ? WHERE isrc = ?");
            statement.setString(1, album.getTitle());
            statement.setString(2, album.getDescription());
            statement.setInt(3, album.getReleaseYear());
            statement.setString(4, album.getArtistFirstName());
            statement.setString(5, album.getArtistLastName());
            statement.setBytes(6, album.getAlbumCover().getAlbumCoverImage());
            statement.setString(7, album.getAlbumCover().getMimeType());
            statement.setString(8, album.getIsrc());
            statement.execute();
            statement.close();
            connection.close();
        } catch (RepException re) {
            System.out.println(re.getMessage());
            re.printStackTrace();
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
    public static void delete(String isrc) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            logManager.addLog(LocalDateTime.now().toString(), ChangeType.UPDATE, isrc);
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement("DELETE FROM album WHERE isrc = ?");
            statement.setString(1, isrc);
            statement.execute();
            statement.close();
            connection.close();
        } catch (RepException re) {
            System.out.println(re.getMessage());
            re.printStackTrace();
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
