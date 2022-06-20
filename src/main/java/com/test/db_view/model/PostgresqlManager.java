package com.test.db_view.model;

import com.test.db_view.controller.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresqlManager implements DbManager {

    @Override
    public List<String> getTables() {

        Connection connection = ConnectionPool.getConnection();
        List<String> result = new ArrayList<>();
        String[] tableTypes = {"TABLE"};

        try {
            DatabaseMetaData md = connection.getMetaData();
            ResultSet resultSet = md.getTables(null, null, null, tableTypes);
            while (resultSet.next()) {
                result.add(resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String createTable(List<String> data) {
        String tableName = data.get(0);
        String columnName = data.get(1);
        String createTableQuery = ("CREATE TABLE " + tableName + " ( " + columnName + " VARCHAR ) ");

        try (
                Connection connection = ConnectionPool.getConnection()
        ) {
            connection.createStatement().executeUpdate(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "New table " + tableName + " was successfully created";
    }
}