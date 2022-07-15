package com.test.db_view.model;

import com.github.freva.asciitable.AsciiTable;
import com.test.db_view.controller.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<String> columnName = new ArrayList<>();
        data.remove(0);

        for (String temp : data) {
            columnName.add(temp + " VARCHAR");
        }

        String createTable = ("CREATE TABLE " + tableName + "(" + Arrays.toString(columnName.toArray()).
                replace("[", "").replace("]", "") + ")");
        try (
                Connection connection = ConnectionPool.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.format("New table %s was successfully created", tableName);
    }

    @Override
    public String clearTable(String tableName) {
        String clearTable = ("TRUNCATE table " + tableName);

        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(clearTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.format("Table %s was successfully cleared", tableName);
    }

    @Override
    public String dropTable(String tableName) {
        String dropTable = ("DROP table " + tableName);

        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.format("Table %s was successfully deleted", tableName);
    }

    @Override
    public String findTable(String tableName) {
        try (Connection connection = ConnectionPool.getConnection();
             ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM " + tableName)) {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            List<String> columnNames = new ArrayList<>();
            List<List<String>> data = new ArrayList<>();

            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(resultSetMetaData.getColumnName(i));
            }

            while (resultSet.next()) {
                List<String> currentRow = new ArrayList<>();
                data.add(currentRow);
                for (int i = 1; i <= columnCount; i++) {
                    currentRow.add(resultSet.getString(i));
                }
            }

            String[] tableColumnNames = columnNames.toArray(String[]::new);

            String[][] tableData = data.stream()
                    .map(l -> l.toArray(String[]::new))
                    .toArray(String[][]::new);

            return AsciiTable.getTable(tableColumnNames, tableData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String insertInTable(List<String> data) {
        String tableName = data.get(0);
        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();
        data.remove(0);

        int index = 0;
        for (String item : data) {
            if (index % 2 == 0) {
                columns.add(item);
            } else {
                String editedItem = "'" + item + "'";
                values.add(editedItem);
            }
            index++;
        }

        String insertQuery = ("INSERT INTO " + tableName + "(" + Arrays.toString(columns.toArray()).
                replace("[", "").replace("]", "") + ")" + " VALUES" +
                "(" + Arrays.toString(values.toArray()).
                replace("[", "").replace("]", "") + ")");

        try (
                Connection connection = ConnectionPool.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return String.format("New row was successfully created in table %s", tableName);
    }
}