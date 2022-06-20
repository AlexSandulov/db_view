package com.test.db_view.controller.command;

import com.test.db_view.controller.ConnectionPool;
import com.test.db_view.view.View;

public class ExitCommand implements Command {

    private final View view;

    public ExitCommand(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String command) {
        String inputValue = command.split(" \\| ")[0];
        return inputValue.equalsIgnoreCase("exit");
    }

    @Override
    public void execute(String command) {
        ConnectionPool.closeConnection();
        view.write("You have successfully closed connection to database");
    }
}
