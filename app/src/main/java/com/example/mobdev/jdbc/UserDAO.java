package com.example.mobdev.jdbc;

import com.example.mobdev.classes.User;
import com.example.mobdev.classes.UserBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class UserDAO {
    private final ExecutorService executor = Executors.newFixedThreadPool(10); // Pool size depending on expected load

    public void createUser(String username, String password, String firstname, String lastname, String email, String aboutMe, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "INSERT INTO tblUser (username, password, firstname, lastname, email, aboutMe) VALUES (?, ?, ?, ?, ?, ?)";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, firstname);
                statement.setString(4, lastname);
                statement.setString(5, email);
                statement.setString(6, aboutMe);
                statement.executeUpdate();
                onSuccess.run();
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    public void getUser(long id, Consumer<User> onResult, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "SELECT * FROM tblUser WHERE id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    User user = new UserBuilder()
                            .setId(resultSet.getLong("id"))
                            .setUsername(resultSet.getString("username"))
                            .setPassword(resultSet.getString("password"))
                            .setFirstName(resultSet.getString("firstname"))
                            .setLastName(resultSet.getString("lastname"))
                            .setEmail(resultSet.getString("email"))
                            .setAboutMe(resultSet.getString("aboutMe"))
                            .setCreatedAt(resultSet.getTimestamp("created_at"))
                            .setUpdatedAt(resultSet.getTimestamp("updated_at"))
                            .createUser();
                    onResult.accept(user);
                } else {
                    onResult.accept(null);
                }
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    public void getAllUsers(Consumer<List<User>> onResult, Consumer<Exception> onError) {
        executor.execute(() -> {
            List<User> users = new ArrayList<>();
            String sql = "SELECT * FROM tblUser";
            try (Connection connection = DatabaseConnection.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    users.add(new UserBuilder()
                            .setId(resultSet.getLong("id"))
                            .setUsername(resultSet.getString("username"))
                            .setPassword(resultSet.getString("password"))
                            .setFirstName(resultSet.getString("firstname"))
                            .setLastName(resultSet.getString("lastname"))
                            .setEmail(resultSet.getString("email"))
                            .setAboutMe(resultSet.getString("aboutMe"))
                            .setCreatedAt(resultSet.getTimestamp("created_at"))
                            .setUpdatedAt(resultSet.getTimestamp("updated_at"))
                            .createUser());
                }
                onResult.accept(users);
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    public void updateUser(long id, String username, String password, String firstname, String lastname, String email, String aboutMe, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "UPDATE tblUser SET username = ?, password = ?, firstname = ?, lastname = ?, email = ?, aboutMe = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, firstname);
                statement.setString(4, lastname);
                statement.setString(5, email);
                statement.setString(6, aboutMe);
                statement.setLong(7, id);
                statement.executeUpdate();
                onSuccess.run();
            } catch (SQLException e) {
                onError.accept(e);
            }
        });
    }

    public void deleteUser(long id, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "DELETE FROM tblUser WHERE id = ?";
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

    public void authenticateUser(String email, String password, Consumer<User> onResult, Consumer<Exception> onError) {
        executor.execute(() -> {
            String sql = "SELECT * FROM tblUser WHERE email = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {

                    if (!resultSet.getString("password").equals(password)) {
                        onError.accept(new Exception("Invalid credentials!"));
                        return;
                    }

                    User user = new UserBuilder()
                            .setId(resultSet.getLong("id"))
                            .setUsername(resultSet.getString("username"))
                            .setPassword(resultSet.getString("password"))
                            .setFirstName(resultSet.getString("firstname"))
                            .setLastName(resultSet.getString("lastname"))
                            .setEmail(resultSet.getString("email"))
                            .setAboutMe(resultSet.getString("aboutMe"))
                            .setCreatedAt(resultSet.getTimestamp("created_at"))
                            .setUpdatedAt(resultSet.getTimestamp("updated_at"))
                            .createUser();
                    onResult.accept(user);
                } else {
                    onResult.accept(null);
                }
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }
}
