package com.example.mobdev.jdbc;


import com.example.mobdev.classes.Rating;
import com.example.mobdev.classes.RatingBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO {

    public void addRating(long userId, long eventId, int rating, String message) throws SQLException {
        String sql = "INSERT INTO tblRating (user_id, event_id, rating, message) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            statement.setLong(2, eventId);
            statement.setInt(3, rating);
            statement.setString(4, message);
            statement.executeUpdate();
        }
    }

    public Rating getRating(long id) throws SQLException {
        String sql = "SELECT * FROM tblRating WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new RatingBuilder().setId(resultSet.getLong("id")).setUserId(resultSet.getLong("user_id")).setEventId(resultSet.getLong("event_id")).setRating(resultSet.getInt("rating")).setMessage(resultSet.getString("message")).setRatedAt(resultSet.getTimestamp("rated_at")).createRating();
            }
        }
        return null;
    }

    public List<Rating> getAllRatings() throws SQLException {
        List<Rating> ratings = new ArrayList<>();
        String sql = "SELECT * FROM tblRating";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                ratings.add(new RatingBuilder().setId(resultSet.getLong("id")).setUserId(resultSet.getLong("user_id")).setEventId(resultSet.getLong("event_id")).setRating(resultSet.getInt("rating")).setMessage(resultSet.getString("message")).setRatedAt(resultSet.getTimestamp("rated_at")).createRating());
            }
        }
        return ratings;
    }

    public void updateRating(long id, int rating, String message) throws SQLException {
        String sql = "UPDATE tblRating SET rating = ?, message = ?, rated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, rating);
            statement.setString(2, message);
            statement.setLong(3, id);
            statement.executeUpdate();
        }
    }

    public void deleteRating(long id) throws SQLException {
        String sql = "DELETE FROM tblRating WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
