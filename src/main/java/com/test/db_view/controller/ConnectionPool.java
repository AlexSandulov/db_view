package com.test.db_view.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionPool {

    private ConnectionPool() {
    }

    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static Connection connect(String databaseName, String userName, String password) {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + databaseName, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
