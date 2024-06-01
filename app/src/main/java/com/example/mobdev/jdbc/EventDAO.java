package com.example.mobdev.jdbc;

import com.example.mobdev.classes.Event;
import com.example.mobdev.classes.EventBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class EventDAO {

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    // Method to create a new event
    public void createEvent(String name, String description, long organizerId, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "INSERT INTO tblEvent (name, description, organizer_id) VALUES (?, ?, ?)";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, description);
                statement.setLong(3, organizerId);
                statement.executeUpdate();
                onSuccess.run();
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    // Method to get an event by id
    public void getEvent(long id, Consumer<Event> onResult, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "SELECT * FROM tblEvent WHERE id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Event event = new EventBuilder()
                            .setId(resultSet.getLong("id"))
                            .setName(resultSet.getString("name"))
                            .setDescription(resultSet.getString("description"))
                            .setOrganizerId(resultSet.getLong("organizer_id"))
                            .setCreatedAt(resultSet.getTimestamp("created_at"))
                            .setUpdatedAt(resultSet.getTimestamp("updated_at"))
                            .createEvent();
                    onResult.accept(event);
                } else {
                    onResult.accept(null);
                }
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    // Method to get all events
    public void getAllEvents(Consumer<List<Event>> onResult, Consumer<Exception> onError) {
        executor.execute(() -> {
            List<Event> events = new ArrayList<>();
            String sql = "SELECT * FROM tblEvent";
            try (Connection connection = DatabaseConnection.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    events.add(new EventBuilder()
                            .setId(resultSet.getLong("id"))
                            .setName(resultSet.getString("name"))
                            .setDescription(resultSet.getString("description"))
                            .setOrganizerId(resultSet.getLong("organizer_id"))
                            .setCreatedAt(resultSet.getTimestamp("created_at"))
                            .setUpdatedAt(resultSet.getTimestamp("updated_at"))
                            .createEvent());
                }
                onResult.accept(events);
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    // Method to update an event
    public void updateEvent(long id, String name, String description, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "UPDATE tblEvent SET name = ?, description = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, description);
                statement.setLong(3, id);
                statement.executeUpdate();
                onSuccess.run();
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    // Method to delete an event
    public void deleteEvent(long id, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "DELETE FROM tblEvent WHERE id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
                onSuccess.run();
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }
}
