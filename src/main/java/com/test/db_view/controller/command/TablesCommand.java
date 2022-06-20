package com.test.db_view.controller.command;

import com.test.db_view.model.DbManager;
import com.test.db_view.view.View;

public class TablesCommand implements Command {

    private final DbManager dbManager;
    private final View view;

    public TablesCommand(DbManager dbManager, View view) {
        this.dbManager = dbManager;
        this.view = view;
    }

    @Override
    public boolean canExecute(String command) {
        String inputValue = command.split(" \\| ")[0];
        return inputValue.equalsIgnoreCase("tables");
    }

    @Override
    public void execute(String command) {
        view.write(dbManager.getTables().toString());
    }
}
