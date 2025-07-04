package com.example;

import java.util.Set;

public class StringCalculator {

    private static final Set<String> DELIMITERS = Set.of(",","\n");

    public int add(String input) {
        if (input == null || input.equals("")) {
            return 0;
        }

        String[] numbers = splitNumbers(input);
        //Works for unknown amount of numbers
        return getSum(numbers);
    }

    private boolean containsDelimiter(String input) {
        return DELIMITERS.stream().anyMatch(input::contains);
    }

    private String[] splitNumbers(String input) {
        // Combine delimiters into a regex separated by "|"
        //works for ',' and '\n' as delimiter
        String delimiterRegex = String.join("|", DELIMITERS);

        return input.split(delimiterRegex);
    }

    private int getSum(String [] numbers){
        int sum = 0;
        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }
        return sum;

    }


}
