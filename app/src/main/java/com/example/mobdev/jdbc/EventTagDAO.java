package com.example.mobdev.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class EventTagDAO {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void addTagToEvent(long eventId, long tagId, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "INSERT INTO tblEventTags (event_id, tag_id) VALUES (?, ?)";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, eventId);
                statement.setLong(2, tagId);
                statement.executeUpdate();
                onSuccess.run();
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    public static void removeTagFromEvent(long eventId, long tagId, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "DELETE FROM tblEventTags WHERE event_id = ? AND tag_id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, eventId);
                statement.setLong(2, tagId);
                statement.executeUpdate();
                onSuccess.run();
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    public static void getTagsForEvent(long eventId, Consumer<List<Long>> onResult, Consumer<Exception> onError) {
        executor.execute(() -> {
            List<Long> tagIds = new ArrayList<>();
            String sql = "SELECT tag_id FROM tblEventTags WHERE event_id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, eventId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    tagIds.add(resultSet.getLong("tag_id"));
                }
                onResult.accept(tagIds);
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    public static void getEventsForTag(long tagId, Consumer<List<Long>> onResult, Consumer<Exception> onError) {
        executor.execute(() -> {
            List<Long> eventIds = new ArrayList<>();
            String sql = "SELECT event_id FROM tblEventTags WHERE tag_id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, tagId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    eventIds.add(resultSet.getLong("event_id"));
                }
                onResult.accept(eventIds);
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }
}
