package com.test.db_view.controller.command;

import com.test.db_view.model.DbManager;
import com.test.db_view.view.View;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CreateCommand implements Command {

    private final DbManager dbManager;
    private final View view;

    public CreateCommand(DbManager dbManager, View view) {
        this.dbManager = dbManager;
        this.view = view;
    }

    @Override
    public boolean canExecute(String command) {
        String inputValue = command.split(" \\| ")[0];
        return inputValue.equalsIgnoreCase("create");
    }

    @Override
    public void execute(String command) {
        String[] data = command.split(" \\| ");
        List<String> dataValues = new LinkedList<>(Arrays.asList(data));
        dataValues.remove(0);
        view.write(dbManager.createTable(dataValues));
    }
}
