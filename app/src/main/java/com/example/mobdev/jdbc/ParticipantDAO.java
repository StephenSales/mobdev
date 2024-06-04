package com.example.mobdev.jdbc;


import com.example.mobdev.classes.Participant;
import com.example.mobdev.classes.ParticipantBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {

    public static void addParticipant(long userId, long eventId, String firstname, String lastname, String email) throws SQLException {
        String sql = "INSERT INTO tblParticipant (user_id, event_id, firstname, lastname, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            statement.setLong(2, eventId);
            statement.setString(3, firstname);
            statement.setString(4, lastname);
            statement.setString(5, email);
            statement.executeUpdate();
        }
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

    public static int getEventParticipants(long eventId) throws SQLException {
        List<Participant> participants = new ArrayList<>();
        String sql = "SELECT * FROM tblParticipant WHERE event_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                participants.add(new ParticipantBuilder().setId(resultSet.getLong("id")).setUserId(resultSet.getLong("user_id")).setEventId(resultSet.getLong("event_id")).setFirstname(resultSet.getString("firstname")).setLastname(resultSet.getString("lastname")).setEmail(resultSet.getString("email")).setJoinedAt(resultSet.getTimestamp("joined_at")).createParticipant());
            }
        }
        return participants.size();
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
