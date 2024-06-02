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
            String sql = "INSERT INTO tblFollowing (user_id, following_id) VALUES (?, ?)";
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

    public static void removeFollowing(long userId, long followingId, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "DELETE FROM tblFollowing WHERE user_id = ? AND following_id = ?";
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
            String sql = "SELECT following_id FROM tblFollowing WHERE user_id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, userId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    followingIds.add(resultSet.getLong("following_id"));
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
            String sql = "SELECT user_id FROM tblFollowing WHERE following_id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, followingId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    followerIds.add(resultSet.getLong("user_id"));
                }
                onResult.accept(followerIds);
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }
}
