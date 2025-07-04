package com.example;

import java.util.Set;

public class StringCalculator {

    private static final Set<String> DELIMITERS = Set.of(",");

    public int add(String input) {
        if (input == null || input.equals("")) {
            return 0;
        }

        if (!containsDelimiter(input)) {
            return Integer.parseInt(input);
        }

        String[] numbers = splitNumbers(input);
        //Works for unknown amount of numbers
        int sum = 0;
        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }
        return sum;

    }

    private boolean containsDelimiter(String input) {
        return DELIMITERS.stream().anyMatch(input::contains);
    }

    private String[] splitNumbers(String input) {
        String delimiter = DELIMITERS.iterator().next();
        return input.split(delimiter);
    }


}
