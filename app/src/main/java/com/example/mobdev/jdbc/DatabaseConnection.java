package com.example.mobdev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.concurrent.Executor;

public class DatabaseConnection {


    private enum Users {
        ADRIAN, RODDNEIL, STEPHEN
    }

    private static final HashMap<Users, String> ip = new HashMap<>();


    static {
        ip.put(Users.ADRIAN, "192.168.43.60");
        ip.put(Users.RODDNEIL, "192.168.1.5");
        ip.put(Users.STEPHEN, "192.168.1.2");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JDBC driver.");
        }
    }

    private static final String IP = ip.get(Users.RODDNEIL);
    private static final String URL = "jdbc:mysql://" + IP + ":3306/take_it";
    private static final String USER = "root_take_it";
    private static final String PASSWORD = "";
    private static final long TIMEOUT = 1000;


    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
