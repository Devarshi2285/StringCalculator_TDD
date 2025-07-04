package com.example;

import java.util.Set;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final String DELIMITERS_REGX = ",|\n";

    public int add(String input) {
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
        for (String number : numbers) {
            int num = Integer.parseInt(number);
            if(num<0){
                throw new IllegalArgumentException("negatives not allowed: " + num);
            }
            sum += num;
        }
        return sum;

    }

}
