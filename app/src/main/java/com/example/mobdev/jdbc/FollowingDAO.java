package com.example.mobdev.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class FollowingDAO {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void addFollowing(long userId, long followingId, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String insertFollowSql = "INSERT INTO tblFollow (follower_id, followed_id) VALUES (?, ?)";
            String insertNotificationSql = "INSERT INTO tblNotification (user_id, message) VALUES (?, ?)";

            try (Connection connection = DatabaseConnection.getConnection()) {
                // Begin a transaction
                connection.setAutoCommit(false);

                // Insert the follow relationship
                try (PreparedStatement statement = connection.prepareStatement(insertFollowSql)) {
                    statement.setLong(1, userId);
                    statement.setLong(2, followingId);
                    statement.executeUpdate();
                }

                // Insert the notification for the followed user
                try (PreparedStatement statement = connection.prepareStatement(insertNotificationSql)) {
                    String message = "You have a new follower!";
                    statement.setLong(1, followingId);
                    statement.setString(2, message);
                    statement.executeUpdate();
                }

                // Commit the transaction
                connection.commit();
                onSuccess.run();
            } catch (SQLException e) {
                // Handle exception and rollback transaction
                try (Connection connection = DatabaseConnection.getConnection()) {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    onError.accept(rollbackEx);
                }
                onError.accept(e);
            }
        });
    }


    public static void removeFollowing(long userId, long followingId, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "DELETE FROM tblFollow WHERE follower_id = ? AND followed_id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, userId);
                statement.setLong(2, followingId);
                statement.executeUpdate();
                onSuccess.run();
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    public static void getFollowings(long userId, Consumer<List<Long>> onResult, Consumer<Exception> onError) {
        executor.execute(() -> {
            List<Long> followingIds = new ArrayList<>();
            String sql = "SELECT followed_id FROM tblFollow WHERE follower_id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, userId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    followingIds.add(resultSet.getLong("followed_id"));
                }
                onResult.accept(followingIds);
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    public static void getFollowers(long followingId, Consumer<List<Long>> onResult, Consumer<Exception> onError) {
        executor.execute(() -> {
            List<Long> followerIds = new ArrayList<>();
            String sql = "SELECT follower_id FROM tblFollow WHERE followed_id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, followingId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    followerIds.add(resultSet.getLong("follower_id"));
                }
                onResult.accept(followerIds);
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }
}
