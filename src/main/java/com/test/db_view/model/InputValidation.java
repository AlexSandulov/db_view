package com.test.db_view.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class InputValidation {

    public boolean connectionDataValidation(String database, String userName, String password) throws IOException {

        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {

            Properties appProps = new Properties();
            appProps.load(input);

            if (database.equals(appProps.getProperty("databaseName"))
                    && userName.equals(appProps.getProperty("userDb"))
                    && password.equals(appProps.getProperty("password"))) {
                return true;
            }
            System.out.println("Entered data is wrong, connection cannot be established.");
            return false;

        }
    }
}
