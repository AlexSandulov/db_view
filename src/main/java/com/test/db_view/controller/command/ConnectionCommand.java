package com.test.db_view.controller.command;

import com.test.db_view.controller.ConnectionPool;
import com.test.db_view.view.View;

import java.sql.Connection;

public class ConnectionCommand implements Command {

    private final View view;

    public ConnectionCommand(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String command) {
        String inputValue = command.split(" \\| ")[0];
        return inputValue.equalsIgnoreCase("connect");
    }

    @Override
    public void execute(String command) {
        String[] arr = command.split("\\|");
        Connection connection = ConnectionPool.connect(arr[1].trim()
                , arr[2].trim(), arr[3].trim());
        view.write("You've been successfully connected to database " + arr[1]);
    }
}
