package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * StringCalculator supports:
 * <ul>
 *   <li>Default delimiters: comma (,) and newline (\n)</li>
 *   <li>Custom single or multiple delimiters, including multi-character delimiters</li>
 *   <li>Ignoring numbers greater than 1000</li>
 *   <li>Throws exception if negative numbers are found</li>
 *   <li>Tracks the number of times add() is called</li>
 * </ul>
 */
public class StringCalculator {

    private static final String DELIMITERS_REGEX = ",|\n";
    private static int addCallCount = 0;

    /**
     * Returns the number of times {@link #add(String)} has been called.
     *
     * @return the call count
     */
    public int getCalledCount() {
        return addCallCount;
    }

    /**
     * Resets the call count for {@link #add(String)}.
     * Useful for running isolated tests without interference.
     */
    public static void resetCallCount() {
        addCallCount = 0;
    }

    /**
     * Adds numbers contained in the given input string using default or custom delimiters.
     * <p>
     * Supports:
     * <ul>
     *   <li>Empty strings, which return 0</li>
     *   <li>Newline characters as delimiters</li>
     *   <li>Single or multiple custom delimiters with variable lengths</li>
     *   <li>Ignores numbers greater than 1000</li>
     *   <li>Throws exception for negative numbers with a list of all negatives found</li>
     * </ul>
     *
     * @param input input string containing numbers and optional delimiter specification
     * @return sum of the valid numbers
     * @throws IllegalArgumentException if negative numbers are present in the input
     */
    public int add(String input) {
        addCallCount++;

        if (input == null || input.isEmpty()) {
            return 0;
        }

        String[] numbers = splitNumbers(input);
        return calculateSum(numbers);
    }

    /**
     * Splits the input string into numbers based on detected delimiters.
     * <p>
     * Supports:
     * <ul>
     *   <li>Default delimiters: comma (,) and newline (\n)</li>
     *   <li>Single custom delimiter declared as //;\n</li>
     *   <li>Multiple custom delimiters declared as //[delim1][delim2]...\n</li>
     *   <li>Multi-character delimiters, e.g., //[***]\n or //[***][%%]\n</li>
     * </ul>
     *
     * @param input the input string to parse
     * @return array of numeric strings split by appropriate delimiters
     */
    private String[] splitNumbers(String input) {
        String delimiterRegex = DELIMITERS_REGEX;
        String numbersPart = input;

        if (input.startsWith("//")) {
            int delimiterEndIdx = input.indexOf("\n");
            String delimiterSection = input.substring(2, delimiterEndIdx);

            delimiterRegex = buildDelimiterRegex(delimiterSection);
            numbersPart = input.substring(delimiterEndIdx + 1);
        }

        return numbersPart.split(delimiterRegex);
    }

    /**
     * Builds a regex pattern combining custom delimiters (single or multiple, any length)
     * with default delimiters. Ensures all delimiters are safely escaped.
     * <p>
     * Supports:
     * <ul>
     *   <li>Single delimiter without brackets, e.g., //;\n</li>
     *   <li>Multiple delimiters enclosed in brackets, e.g., //[;][%]\n or //[***][%%]\n</li>
     * </ul>
     *
     * @param delimiterSection the section of the input declaring custom delimiters
     * @return combined regex string for splitting numbers
     */
    private String buildDelimiterRegex(String delimiterSection) {
        StringBuilder combinedRegex = new StringBuilder();

        if (delimiterSection.startsWith("[") && delimiterSection.contains("]")) {
            int idx = 0;
            while (idx < delimiterSection.length()) {
                int open = delimiterSection.indexOf('[', idx);
                int close = delimiterSection.indexOf(']', open);
                if (open == -1 || close == -1) break;

                String delim = delimiterSection.substring(open + 1, close);
                if (!delim.isEmpty()) {
                    if (combinedRegex.length() > 0) combinedRegex.append("|");
                    combinedRegex.append(Pattern.quote(delim));
                }
                idx = close + 1;
            }
        } else {
            combinedRegex.append(Pattern.quote(delimiterSection));
        }

        combinedRegex.append("|").append(DELIMITERS_REGEX);
        return combinedRegex.toString();
    }

    /**
     * Calculates the sum of the given numbers.
     * <p>
     * Ignores numbers greater than 1000 and throws an exception if negative numbers are present.
     *
     * @param numbers array of numeric strings
     * @return the calculated sum of valid numbers
     * @throws IllegalArgumentException if negative numbers are found
     */
    private int calculateSum(String[] numbers) {
        int sum = 0;
        List<Integer> negativeNumbers = new ArrayList<>();

        for (String numStr : numbers) {
            if (numStr.isEmpty()) {
                continue; // skip empty parts (like after "//;\n")
            }

            int num = Integer.parseInt(numStr);


            if (num < 0) {
                negativeNumbers.add(num);
            } else if (num <= 1000) {
                sum += num;
            }
        }

        checkAndThrowIfNegativesFound(negativeNumbers);
        return sum;
    }

    /**
     * Throws an {@link IllegalArgumentException} listing all negatives
     * if any were found during calculation.
     *
     * @param negatives list of detected negative numbers
     * @throws IllegalArgumentException with a message containing all negative numbers
     */
    private void checkAndThrowIfNegativesFound(List<Integer> negatives) {
        if (!negatives.isEmpty()) {
            String negativesStr = String.join(", ", negatives.stream()
                    .map(String::valueOf)
                    .toArray(String[]::new));
            throw new IllegalArgumentException("negatives not allowed: " + negativesStr);
        }
    }
}
