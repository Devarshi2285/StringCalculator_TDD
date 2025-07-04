package com.example;

public class StringCalculator {

    public int add(String input) {
        if (input == null || input.equals("")) {
            return 0;
        }

        return Integer.parseInt(input.trim());

    }


}
