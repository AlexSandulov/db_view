package com.test.db_view.model;

import java.util.List;

public interface DbManager {

    List<String> getTables();

    String createTable(List<String> data);
}
