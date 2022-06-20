package com.test.db_view.controller.command;

public interface Command {

    boolean canExecute(String command);

    void execute(String command);
}
