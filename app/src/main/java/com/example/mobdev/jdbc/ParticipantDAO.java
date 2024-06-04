package com.example.mobdev.jdbc;


import com.example.mobdev.classes.Participant;
import com.example.mobdev.classes.ParticipantBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class ParticipantDAO {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void addParticipant(long userId, long eventId, String firstname, String lastname, String email, Runnable onSuccess, Consumer<Exception> onError) {
        executor.execute(() -> {
            String insertParticipantSql = "INSERT INTO tblParticipant (user_id, event_id, firstname, lastname, email) VALUES (?, ?, ?, ?, ?)";
            String fetchOrganizerSql = "SELECT organizer_id FROM tblEvent WHERE id = ?";
            String insertNotificationSql = "INSERT INTO tblNotification (user_id, message) VALUES (?, ?)";

            try (Connection connection = DatabaseConnection.getConnection()) {
                // Begin a transaction
                connection.setAutoCommit(false);

                // Insert the participant
                try (PreparedStatement statement = connection.prepareStatement(insertParticipantSql)) {
                    statement.setLong(1, userId);
                    statement.setLong(2, eventId);
                    statement.setString(3, firstname);
                    statement.setString(4, lastname);
                    statement.setString(5, email);
                    statement.executeUpdate();
                }

                // Fetch the organizer's ID
                long organizerId;
                try (PreparedStatement statement = connection.prepareStatement(fetchOrganizerSql)) {
                    statement.setLong(1, eventId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            organizerId = resultSet.getLong("organizer_id");
                        } else {
                            throw new SQLException("Organizer not found for event ID " + eventId);
                        }
                    }
                }

                // Insert the notification for the organizer
                try (PreparedStatement statement = connection.prepareStatement(insertNotificationSql)) {
                    String message = "A new participant has joined your event.";
                    statement.setLong(1, organizerId);
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



    public static Participant getParticipant(long id) throws SQLException {
        String sql = "SELECT * FROM tblParticipant WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new ParticipantBuilder().setId(resultSet.getLong("id")).setUserId(resultSet.getLong("user_id")).setEventId(resultSet.getLong("event_id")).setFirstname(resultSet.getString("firstname")).setLastname(resultSet.getString("lastname")).setEmail(resultSet.getString("email")).setJoinedAt(resultSet.getTimestamp("joined_at")).createParticipant();
            }
        }
        return null;
    }

    public static List<Participant> getAllParticipants() throws SQLException {
        List<Participant> participants = new ArrayList<>();
        String sql = "SELECT * FROM tblParticipant";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                participants.add(new ParticipantBuilder().setId(resultSet.getLong("id")).setUserId(resultSet.getLong("user_id")).setEventId(resultSet.getLong("event_id")).setFirstname(resultSet.getString("firstname")).setLastname(resultSet.getString("lastname")).setEmail(resultSet.getString("email")).setJoinedAt(resultSet.getTimestamp("joined_at")).createParticipant());
            }
        }
        return participants;
    }

    public static void updateParticipant(long id, long userId, long eventId, String firstname, String lastname, String email) throws SQLException {
        String sql = "UPDATE tblParticipant SET user_id = ?, event_id = ?, firstname = ?, lastname = ?, email = ?, joined_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            statement.setLong(2, eventId);
            statement.setString(3, firstname);
            statement.setString(4, lastname);
            statement.setString(5, email);
            statement.setLong(6, id);
            statement.executeUpdate();
        }
    }

    public static void deleteParticipant(long id) throws SQLException {
        String sql = "DELETE FROM tblParticipant WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
