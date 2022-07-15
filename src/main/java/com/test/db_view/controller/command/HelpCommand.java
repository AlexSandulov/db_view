package com.test.db_view.controller.command;

import com.test.db_view.view.View;

public class HelpCommand implements Command{

    private final View view;

    public HelpCommand(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String command) {
        String inputValue = command.split(" \\| ")[0];
        return inputValue.equalsIgnoreCase("help");
    }

    @Override
    public void execute(String command) { //add text to file and read from file | IO apache
        view.write("The list of available commands:");
        view.write("connect - command to connect to database. Format - connect | databaseName | userName | password");
        view.write("tables - command to show all tables in current database. Format - tables");
        view.write("clear - command to clear all data from selected table. Format - clear | tableName");
        view.write("drop - command to delete selected table. Format - drop | tableName");
        view.write("create - command to create a new table with columns. Format - create | column1 | column2 | ... " +
                "| columnN");
        view.write("find - command to find and show data from selected table. Format - find | tableName");
        view.write("insert - command to add one row into selected table. Format - insert | tableName | column1 | value1 |" +
                "column2 | value2 | ... | columnN | valueN");
        view.write("update - command to update the value1 in defined column1 where column2 has value2. Format - update |" +
                " tableName | column1 | value1 | column2 | value2");
        view.write("delete - command to delete one or several records in selected table where column = value. Format - " +
                "delete | tableName | column | value");
        view.write("exit - command to disconnect connection to the current database. Format - exit");
        view.write("stop - command to stop application. Format - stop");
    }
}
