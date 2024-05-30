package com.example.mobdev.jdbc;


import com.example.mobdev.classes.Tag;
import com.example.mobdev.classes.TagBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagDAO {

    public void createTag(String name) throws SQLException {
        String sql = "INSERT INTO tblTags (name) VALUES (?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
        }
    }

    public Tag getTag(long id) throws SQLException {
        String sql = "SELECT * FROM tblTags WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new TagBuilder().setId(resultSet.getLong("id")).setName(resultSet.getString("name")).createTag();
            }
        }
        return null;
    }

    public List<Tag> getAllTags() throws SQLException {
        List<Tag> tags = new ArrayList<>();
        String sql = "SELECT * FROM tblTags";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                tags.add(new TagBuilder().setId(resultSet.getLong("id")).setName(resultSet.getString("name")).createTag());
            }
        }
        return tags;
    }

    public void updateTag(long id, String name) throws SQLException {
        String sql = "UPDATE tblTags SET name = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setLong(2, id);
            statement.executeUpdate();
        }
    }

    public void deleteTag(long id) throws SQLException {
        String sql = "DELETE FROM tblTags WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
