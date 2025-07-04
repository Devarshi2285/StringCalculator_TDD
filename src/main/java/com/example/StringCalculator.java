package com.example;

public class StringCalculator {

    public int add(String input) {
        if (input == null || input.equals("")) {
            return 0;
        }

        if (!input.contains(",")) {
            return Integer.parseInt(input);
        }

        String[] numbers = input.split(",");
        return Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);

    }


}
