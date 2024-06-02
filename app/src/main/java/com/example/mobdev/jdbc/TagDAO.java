package com.example.mobdev.jdbc;

import com.example.mobdev.classes.Tag;
import com.example.mobdev.classes.TagBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class TagDAO {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void addTag(String name, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "INSERT INTO tblTags (name) VALUES (?)";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, name);
                statement.executeUpdate();
                onSuccess.run();
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    public static void getTag(long id, Consumer<Tag> onResult, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "SELECT * FROM tblTags WHERE id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Tag tag = new TagBuilder()
                            .setId(resultSet.getLong("id"))
                            .setName(resultSet.getString("name"))
                            .createTag();
                    onResult.accept(tag);
                } else {
                    onResult.accept(null);
                }
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    public static void getAllTags(Consumer<List<Tag>> onResult, Consumer<Exception> onError) {
        executor.execute(() -> {
            List<Tag> tags = new ArrayList<>();
            String sql = "SELECT * FROM tblTags";
            try (Connection connection = DatabaseConnection.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    tags.add(new TagBuilder()
                            .setId(resultSet.getLong("id"))
                            .setName(resultSet.getString("name"))
                            .createTag());
                }
                onResult.accept(tags);
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    public static void updateTag(long id, String name, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "UPDATE tblTags SET name = ? WHERE id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setLong(2, id);
                statement.executeUpdate();
                onSuccess.run();
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    public static void deleteTag(long id, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "DELETE FROM tblTags WHERE id = ?";
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
