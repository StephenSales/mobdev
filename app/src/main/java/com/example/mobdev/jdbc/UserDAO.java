package com.example.mobdev.jdbc;

import com.example.mobdev.classes.User;
import com.example.mobdev.classes.UserBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Method to create a new user
    public void createUser(String username, String password, String firstname, String lastname, String email, String aboutMe) throws SQLException {
        String sql = "INSERT INTO tblUser (username, password, firstname, lastname, email, aboutMe) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, firstname);
            statement.setString(4, lastname);
            statement.setString(5, email);
            statement.setString(6, aboutMe);
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
                        .setUsername(resultSet.getString("username"))
                        .setPassword(resultSet.getString("password"))
                        .setFirstname(resultSet.getString("firstname"))
                        .setLastname(resultSet.getString("lastname"))
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
                        .setUsername(resultSet.getString("username"))
                        .setPassword(resultSet.getString("password"))
                        .setFirstname(resultSet.getString("firstname"))
                        .setLastname(resultSet.getString("lastname"))
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
    public void updateUser(long id, String username, String password, String firstname, String lastname, String email, String aboutMe) throws SQLException {
        String sql = "UPDATE tblUser SET username = ?, password = ?, firstname = ?, lastname = ?, email = ?, aboutMe = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, firstname);
            statement.setString(4, lastname);
            statement.setString(5, email);
            statement.setString(6, aboutMe);
            statement.setLong(7, id);
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
