package com.example.mobdev.jdbc;

        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

public class FollowDAO {

    // Method to follow a user
    public static void followUser(long followerId, long followedId) throws SQLException {
        String sql = "INSERT INTO tblFollow (follower_id, followed_id) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, followerId);
            statement.setLong(2, followedId);
            statement.executeUpdate();
        }
    }

    // Method to unfollow a user
    public static void unfollowUser(long followerId, long followedId) throws SQLException {
        String sql = "DELETE FROM tblFollow WHERE follower_id = ? AND followed_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, followerId);
            statement.setLong(2, followedId);
            statement.executeUpdate();
        }
    }

    // Method to get the list of users followed by a specific user
    public static List<Long> getFollowedUsers(long followerId) throws SQLException {
        List<Long> followedUsers = new ArrayList<>();
        String sql = "SELECT followed_id FROM tblFollow WHERE follower_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, followerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                followedUsers.add(resultSet.getLong("followed_id"));
            }
        }
        return followedUsers;
    }

    // Method to get the list of users following a specific user
    public static List<Long> getFollowers(long followedId) throws SQLException {
        List<Long> followers = new ArrayList<>();
        String sql = "SELECT follower_id FROM tblFollow WHERE followed_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, followedId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                followers.add(resultSet.getLong("follower_id"));
            }
        }
        return followers;
    }

    // Method to check if a user is following another user
    public static boolean isFollowing(long followerId, long followedId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tblFollow WHERE follower_id = ? AND followed_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, followerId);
            statement.setLong(2, followedId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }
}
