package com.test.db_view.view;

import com.test.db_view.controller.Controller;
import com.test.db_view.model.InputValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Request {

    //ask about code injection (Controller)

    public void parseInput() throws SQLException, IOException {

        String option;
        String proceed = "yes";
        Scanner sc = new Scanner(System.in);

        while ("yes".equalsIgnoreCase(proceed)) {

            Greetings greetings = new Greetings();
            Controller controller = new Controller();
            InputValidation validation = new InputValidation();

            greetings.greetings("Hello, User! Please make a choice, you can start program or type help " +
                    "to see all available commands");
            option = sc.nextLine();
            String[] arr = option.split(" , ");
            switch (arr[0].toLowerCase()) {
                case "connect":
                    if (validation.connectionDataValidation(arr[1], arr[2], arr[3])) {
                        controller.connectToDatabase(arr[2], arr[3]);
                    }
                    break;

                case "tables":

                    break;

                case "clear":

                    break;

                case "drop":

                    break;

                case "create":

                    break;

                case "find":

                    break;

                case "insert":

                    break;

                case "update":

                    break;

                case "delete":

                    break;

                case "help":

                    break;

                case "exit":

                    break;

                default:
                    System.out.println("Not valid command");
                    break;
            }
            System.out.print("Do you want to continue? ");
            proceed = sc.next();
        }
        sc.close();
    }
}
