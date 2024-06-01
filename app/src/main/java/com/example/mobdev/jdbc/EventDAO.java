package com.example.mobdev.jdbc;

import com.example.mobdev.classes.Event;
import com.example.mobdev.classes.EventBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    public void createEvent(String name, String description, long organizerId) throws SQLException {
        String sql = "INSERT INTO tblEvent (name, description, organizer_id) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setLong(3, organizerId);
            statement.executeUpdate();
        }
    }

    public Event getEvent(long id) throws SQLException {
        String sql = "SELECT * FROM tblEvent WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new EventBuilder().setId(resultSet.getLong("id")).setName(resultSet.getString("name")).setDescription(resultSet.getString("description")).setOrganizerId(resultSet.getLong("organizer_id")).setCreatedAt(resultSet.getTimestamp("created_at")).setUpdatedAt(resultSet.getTimestamp("updated_at")).createEvent();
            }
        }
        return null;
    }

    public List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM tblEvent";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                events.add(new EventBuilder().setId(resultSet.getLong("id")).setName(resultSet.getString("name")).setDescription(resultSet.getString("description")).setOrganizerId(resultSet.getLong("organizer_id")).setCreatedAt(resultSet.getTimestamp("created_at")).setUpdatedAt(resultSet.getTimestamp("updated_at")).createEvent());
            }
        }
        return events;
    }

    public void updateEvent(long id, String name, String description) throws SQLException {
        String sql = "UPDATE tblEvent SET name = ?, description = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setLong(3, id);
            statement.executeUpdate();
        }
    }

    public void deleteEvent(long id) throws SQLException {
        String sql = "DELETE FROM tblEvent WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
