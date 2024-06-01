package com.example.mobdev.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventTagDAO {

    public void addTagToEvent(long eventId, long tagId) throws SQLException {
        String sql = "INSERT INTO tblEventTags (event_id, tag_id) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, eventId);
            statement.setLong(2, tagId);
            statement.executeUpdate();
        }
    }

    public void removeTagFromEvent(long eventId, long tagId) throws SQLException {
        String sql = "DELETE FROM tblEventTags WHERE event_id = ? AND tag_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, eventId);
            statement.setLong(2, tagId);
            statement.executeUpdate();
        }
    }

    public List<Long> getTagsForEvent(long eventId) throws SQLException {
        List<Long> tagIds = new ArrayList<>();
        String sql = "SELECT tag_id FROM tblEventTags WHERE event_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tagIds.add(resultSet.getLong("tag_id"));
            }
        }
        return tagIds;
    }

    public List<Long> getEventsForTag(long tagId) throws SQLException {
        List<Long> eventIds = new ArrayList<>();
        String sql = "SELECT event_id FROM tblEventTags WHERE tag_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, tagId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                eventIds.add(resultSet.getLong("event_id"));
            }
        }
        return eventIds;
    }
}

