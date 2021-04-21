package com.spicecrispies.persistence;

import com.spicecrispies.core.entities.User;

import java.sql.*;
import java.util.ArrayList;

public class UserMapper extends DataMapper {

    public static User select(String username) throws ClassNotFoundException, SQLException {
        User user = null;
        Connection connection;
        PreparedStatement statement;
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.prepareStatement("SELECT * FROM user WHERE name = ?");
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user = new User(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("password")
            );
        }
        resultSet.close();
        statement.close();
        connection.close();
        return user;
    }

    public static ArrayList<User> selectAll() throws ClassNotFoundException, SQLException {
        ArrayList<User> users = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.createStatement();
        String sql = "SELECT * FROM user";
        ResultSet resultSet = statement.executeQuery(sql);
        User user;
        while (resultSet.next()) {
            user = new User(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("password")
            );
            users.add(user);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return users;
    }

    public static void insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.prepareStatement("INSERT INTO user(id, name, password) VALUES (?,?,?)");
        statement.setString(1, user.getId());
        statement.setString(2, user.getName());
        statement.setString(3, user.getPassword());
        statement.execute();
        statement.close();
        connection.close();
    }

    public static void delete(String id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
        statement.setString(1, id);
        statement.execute();
        statement.close();
        connection.close();
    }
}
