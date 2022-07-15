package com.test.db_view.controller.command;

import com.test.db_view.model.DbManager;
import com.test.db_view.view.View;

public class DropCommand implements Command{

    private final DbManager dbManager;
    private final View view;

    public DropCommand(DbManager dbManager, View view) {
        this.dbManager = dbManager;
        this.view = view;
    }

    @Override
    public boolean canExecute(String command) {
        String inputValue = command.split(" \\| ")[0];
        return inputValue.equalsIgnoreCase("drop");
    }

    @Override
    public void execute(String command) {
        String tableName = command.split(" \\| ")[1];
        view.write(dbManager.dropTable(tableName));
    }
}
