package com.example.mobdev.jdbc;

import com.example.mobdev.classes.User;
import com.example.mobdev.classes.UserBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Method to create a new user
    public static void createUser(String name, String password, String email) throws SQLException {
        String sql = "INSERT INTO tblUser (name, password, email) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.executeUpdate();
        }
    }

    // Method to get a user by id
    public User getUser(long id) throws SQLException {
        String sql = "SELECT * FROM tblUser WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new UserBuilder()
                        .setId(resultSet.getLong("id"))
                        .setName(resultSet.getString("name"))
                        .setPassword(resultSet.getString("password"))
                        .setEmail(resultSet.getString("email"))
                        .setAboutMe(resultSet.getString("aboutMe"))
                        .setCreatedAt(resultSet.getTimestamp("created_at"))
                        .setUpdatedAt(resultSet.getTimestamp("updated_at"))
                        .createUser();
            }
        }
        return null;
    }

    // Method to get a user by email and password
    public static User getUser(String email, String password) throws SQLException {
        String sql = "SELECT * FROM tblUser WHERE email = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new UserBuilder()
                        .setId(resultSet.getLong("id"))
                        .setName(resultSet.getString("name"))
                        .setPassword(resultSet.getString("password"))
                        .setEmail(resultSet.getString("email"))
                        .setAboutMe(resultSet.getString("aboutMe"))
                        .setCreatedAt(resultSet.getTimestamp("created_at"))
                        .setUpdatedAt(resultSet.getTimestamp("updated_at"))
                        .createUser();
            }
        }
        return null;
    }

    // Method to get all users
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM tblUser";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                users.add(new UserBuilder()
                        .setId(resultSet.getLong("id"))
                        .setName(resultSet.getString("name"))
                        .setPassword(resultSet.getString("password"))
                        .setEmail(resultSet.getString("email"))
                        .setAboutMe(resultSet.getString("aboutMe"))
                        .setCreatedAt(resultSet.getTimestamp("created_at"))
                        .setUpdatedAt(resultSet.getTimestamp("updated_at"))
                        .createUser());
            }
        }
        return users;
    }

    // Method to update a user
    public void updateUser(long id, String name, String password, String email, String aboutMe) throws SQLException {
        String sql = "UPDATE tblUser SET username = ?, password = ?, email = ?, aboutMe = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, aboutMe);
            statement.setLong(5, id);
            statement.executeUpdate();
        }
    }

    // Method to delete a user
    public void deleteUser(long id) throws SQLException {
        String sql = "DELETE FROM tblUser WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
