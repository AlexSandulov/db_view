package com.test.db_view.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Controller {

    public void connectToDatabase(String userName, String password) throws IOException, SQLException {

        InputStream input = new FileInputStream("src/main/resources/application.properties");

        Properties appProps = new Properties();
        appProps.load(input);

        String connectionUrl = appProps.getProperty("connectionUrl");

        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password)) {
            System.out.println("You've been successfully connected to database");
        }
    }
}
