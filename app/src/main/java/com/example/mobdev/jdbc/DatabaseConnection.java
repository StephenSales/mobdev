package com.example.mobdev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Executor;

public class DatabaseConnection {

    private static final String IP = "192.168.212.235";

    private static final String URL = "jdbc:mysql://" + IP + ":3306/take_it";
    private static final String USER = "root_take_it";
    private static final String PASSWORD = "";
    private static final long TIMEOUT = 1000;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JDBC driver.");
        }
    }

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
