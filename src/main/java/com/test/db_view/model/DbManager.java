package com.test.db_view.model;

import java.util.List;

public interface DbManager {

    List<String> getTables();

    String createTable(List<String> data);

    String clearTable (String tableName);

    String dropTable (String tableName);

    String findTable(String tableName);

    String insertInTable(List<String> data);
}
