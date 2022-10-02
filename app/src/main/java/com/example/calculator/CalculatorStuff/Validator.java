package com.example.calculator.CalculatorStuff;
import java.util.ArrayList;

public class Validator {

    //validate steps:
    /*
     * 1-check the length before and after tokenization Done
     * 2-check the equality of parentheses  Done
     * 3-check for operation between every to components
     */
    public static boolean isValid(String textEquation, ArrayList<String> tokenizedEquation) {
        String temp = "";
        int balancedParentheses = 0, balancedBars = 0;
        for (String partOfEq : tokenizedEquation) {

            temp += partOfEq;

            if (partOfEq.equals("(")) {
                balancedParentheses++;
            } else if (partOfEq.equals(")")) {
                balancedParentheses--;
            }
            if (partOfEq.equals("|")) {
                balancedBars++;
            }

        }

        if (temp.length() != textEquation.length() || balancedParentheses != 0 || balancedBars % 2 != 0) {
            return false;
        }

        return true;
    }

}
