package com.test.db_view.controller;

import com.test.db_view.controller.command.*;
import com.test.db_view.model.DbManager;
import com.test.db_view.view.View;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<Command> commands;

    private View view;


    public Controller(View view, DbManager dbManager) {
        this.view = view;
        commands = new ArrayList<>();
        commands.add(new ConnectionCommand(view));
        commands.add(new TablesCommand(dbManager, view));
        commands.add(new CreateCommand(dbManager, view));
        commands.add(new ExitCommand(view));
        commands.add(new ClearCommand(dbManager, view));
        commands.add(new DropCommand(dbManager, view));
        commands.add(new FindCommand(dbManager, view));
        commands.add(new HelpCommand(view));
        commands.add(new InsertCommand(dbManager, view));
    }

    public void run() {

        view.write("Hello, User! Please make a choice, you can start program or type help " +
                "to see all available commands");
        String input = view.read();
        while (!input.equalsIgnoreCase("stop")) {

            for (Command command : commands) {
                if (command.canExecute(input)) {
                    command.execute(input);
                    break;
                }
            }

            view.write("Please enter new command");
            input = view.read();
        }
        view.write("Work with this app has been stopped");
        System.exit(0);
    }
}
