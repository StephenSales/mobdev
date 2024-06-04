package com.example.mobdev.jdbc;

import com.example.mobdev.classes.Event;
import com.example.mobdev.classes.EventBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class BookmarkDAO {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);


    public static void addBookmark(long userId, long eventId, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String checkSql = "SELECT COUNT(*) FROM tblBookmark WHERE user_id = ? AND event_id = ?";
            String insertSql = "INSERT INTO tblBookmark (user_id, event_id) VALUES (?, ?)";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement checkStatement = connection.prepareStatement(checkSql);
                 PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {

                // Check if the bookmark already exists
                checkStatement.setLong(1, userId);
                checkStatement.setLong(2, eventId);
                ResultSet resultSet = checkStatement.executeQuery();

                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    // Bookmark already exists
                    onSuccess.run();  // Or handle it differently, e.g., call onError with a specific exception
                    return;
                }

                // Insert the new bookmark
                insertStatement.setLong(1, userId);
                insertStatement.setLong(2, eventId);
                insertStatement.executeUpdate();
                onSuccess.run();
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }


    public static void removeBookmark(long userId, long eventId, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "DELETE FROM tblBookmark WHERE user_id = ? AND event_id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, userId);
                statement.setLong(2, eventId);
                statement.executeUpdate();
                onSuccess.run();
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    public static void getBookmarksByUser(long userId, Consumer<List<Event>> onResult, Consumer<Exception> onError) {
        executor.execute(() -> {
            List<Event> events = new ArrayList<>();
            String sql = "SELECT e.id, e.name, e.description, e.location, e.event_date, e.price, e.organizer_id, e.created_at, e.updated_at " +
                    "FROM tblBookmark b " +
                    "JOIN tblEvent e ON b.event_id = e.id " +
                    "WHERE b.user_id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, userId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Event event = new EventBuilder()
                            .setId(resultSet.getLong("id"))
                            .setName(resultSet.getString("name"))
                            .setDescription(resultSet.getString("description"))
                            .setLocation(resultSet.getString("location"))
                            .setEventDate(resultSet.getTimestamp("event_date"))
                            .setPrice(resultSet.getDouble("price"))
                            .setOrganizerId(resultSet.getLong("organizer_id"))
                            .setCreatedAt(resultSet.getTimestamp("created_at"))
                            .setUpdatedAt(resultSet.getTimestamp("updated_at"))
                            .createEvent();
                    events.add(event);
                }
                onResult.accept(events);
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }


    public static void getBookmarksByEvent(long eventId, Consumer<List<Long>> onResult, Consumer<Exception> onError) {
        executor.execute(() -> {
            List<Long> userIds = new ArrayList<>();
            String sql = "SELECT user_id FROM tblBookmark WHERE event_id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, eventId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    userIds.add(resultSet.getLong("user_id"));
                }
                onResult.accept(userIds);
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }
}

