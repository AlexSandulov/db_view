package com.test.db_view;

import com.test.db_view.model.PostgresqlManager;
import com.test.db_view.view.Console;
import com.test.db_view.controller.Controller;

public class MainClass {

    //connect => connect | retaildb | postgres | PostgresPassword
    //create => create | tableName | column1 | column2 | ... | columnN

    static String userDb = "postgres";
    static String password = "PostgresPassword";
    static String connectionUrl = "jdbc:postgresql://localhost:5432/retaildb";

    public static void main(String[] args) {

        Controller controller = new Controller(new Console(), new PostgresqlManager());
        controller.run();

    }
}
