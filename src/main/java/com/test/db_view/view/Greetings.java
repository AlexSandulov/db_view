package com.test.db_view.view;

public class Greetings {

    private String text = "";

    public void greetings(String greetingText) {
        text = greetingText;
        System.out.println(greetingText);
    }

    public String getText() {
        return text;
    }

}
