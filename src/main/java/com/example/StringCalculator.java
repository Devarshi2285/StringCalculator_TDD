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
        String delimiterRegex = DELIMITERS_REGX;
        String numbersPart = input;

        if (input.startsWith("//")) {
            int delimiterEndIdx = input.indexOf("\n");
            String delimiterSection = input.substring(2, delimiterEndIdx);

            delimiterRegex = buildDelimiterRegex(delimiterSection);

            numbersPart = input.substring(delimiterEndIdx + 1);
        }

        return numbersPart.split(delimiterRegex);
    }

    private String buildDelimiterRegex(String delimiterSection) {
        String customDelimiter;
        if (delimiterSection.startsWith("[") && delimiterSection.endsWith("]")) {
            customDelimiter = delimiterSection.substring(1, delimiterSection.length() - 1);
        } else {
            customDelimiter = delimiterSection;
        }
        return Pattern.quote(customDelimiter) + "|" + DELIMITERS_REGX;
    }



    private int getSum(String [] numbers){

        int sum = 0;
        List<Integer>negativeNumbers= new ArrayList<>();
        for (String number : numbers) {
            int num = Integer.parseInt(number);
            if (isNegative(num, negativeNumbers)) {
                continue; // negative was added to list; continue processing
            }
            if(num>1000){
                continue;
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
