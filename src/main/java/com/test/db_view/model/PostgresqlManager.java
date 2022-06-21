package com.test.db_view.model;

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

        for (String temp : data){
            columnName.add(temp + " VARCHAR");
        }

        String createTable = ("CREATE TABLE " + tableName + "("+ Arrays.toString(columnName.toArray()).
                replace("[", "").replace("]", "") +")");
                try (
                Connection connection = ConnectionPool.getConnection()
        ) {
            connection.createStatement().executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "New table " + tableName + " was successfully created";
    }
}