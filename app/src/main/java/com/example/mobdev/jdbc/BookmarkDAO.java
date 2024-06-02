package com.example.mobdev.jdbc;

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
            String sql = "INSERT INTO tblBookmark (user_id, event_id) VALUES (?, ?)";
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

    public static void getBookmarksByUser(long userId, Consumer<List<Long>> onResult, Consumer<Exception> onError) {
        executor.execute(() -> {
            List<Long> eventIds = new ArrayList<>();
            String sql = "SELECT event_id FROM tblBookmark WHERE user_id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, userId);
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

