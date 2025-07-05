package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final String DELIMITERS_REGX = ",|\n";
    private static int addCallCount=0;

    public int getCalledCount() {
        return addCallCount;
    }

    public static void resetCallCount() {
        addCallCount = 0;
    }

    public int add(String input) {
        addCallCount++;
        if (input == null || input.equals("")) {
            return 0;
        }

        String[] numbers = splitNumbers(input);
        //Works for unknown amount of numbers
        return getSum(numbers);
    }

    private String[] splitNumbers(String input) {
        // Combine delimiters into a regex separated by "|"
        //works for ',' and '\n' as delimiter and variable delimiter

        String delimiterRegex = DELIMITERS_REGX;


        if(input.startsWith("//")){
            String numberPart = "";
            String customDelimiter = input.substring(2, 3);
            delimiterRegex = Pattern.quote(customDelimiter) ;
            numberPart = input.substring(4);
            return numberPart.split(delimiterRegex);
        }

        return input.split(delimiterRegex);

    }

    private int getSum(String [] numbers){

        int sum = 0;
        List<Integer>negativeNumbers= new ArrayList<>();
        for (String number : numbers) {
            int num = Integer.parseInt(number);
            if (isNegative(num, negativeNumbers)) {
                continue; // negative was added to list; continue processing
            }
            sum += num;
        }

        //handle any number of -ve number and throws exception
        checkAndThrowIfNegativesFound(negativeNumbers);
        return sum;

    }

    /**
     * Checks if the number is negative; if so, adds to negatives list.
     * Returns true if number was negative.
     */
    private boolean isNegative(int n, List<Integer> negatives) {
        if (n < 0) {
            negatives.add(n);
            return true;
        }
        return false;
    }

    /**
     * Throws an exception if any negatives were found.
     */
    private void checkAndThrowIfNegativesFound(List<Integer> negatives) {
        if (!negatives.isEmpty()) {
            String negativesStr = negatives.stream()
                    .map(String::valueOf)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");
            throw new IllegalArgumentException("negatives not allowed: " + negativesStr);
        }
    }

}
