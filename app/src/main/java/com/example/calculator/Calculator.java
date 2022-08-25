package com.example.calculator;
import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {

    public static double add(double num1, double num2) {
        return num1 + num2;
    }

    public static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    public static double minus(double num1, double num2) {
        return num1 - num2;
    }

    public static double divide(double num1, double num2) {
        return num1 / num2;
    }

    public static double modulus(int num1, int num2) {
        return num1 % num2;
    }

    public static BigDecimal calculate(ArrayList<String> eq) {

        BigDecimal result;
        BigDecimal []result2;

        for (int i = 1; i < eq.size(); i += 2) {

            if (i + 1 == eq.size()) {
                break;
            } else if (eq.get(i).charAt(0) == '%') {
                BigDecimal firstNumber = new BigDecimal(eq.get(i-1));
                BigDecimal secondNumber = new BigDecimal(eq.get(i+1));
                result = firstNumber.remainder(secondNumber);
                eq.set(i + 1, result.toString());
                eq.remove(i - 1);
                eq.remove(i - 1);
                i = i-1;
            }
        }

        for (int i = 1; i < eq.size(); i += 2) {

            if (i + 1 == eq.size()) {
                break;
            }
            else if (eq.get(i).charAt(0) == '/') {

                BigDecimal firstNumber = new BigDecimal(eq.get(i-1));
                BigDecimal secondNumber = new BigDecimal(eq.get(i+1));
                result = new BigDecimal(firstNumber.doubleValue() / secondNumber.doubleValue());
                eq.set(i + 1, result.toString());
                eq.remove(i - 1);
                eq.remove(i - 1);
                i = i-1;
            }
        }

        for (int i = 1; i < eq.size(); i += 2) {

            if (i + 1 == eq.size()) {
                break;
            }
            else if (eq.get(i).charAt(0) == 'x') {
                BigDecimal firstNumber = new BigDecimal(eq.get(i-1));
                BigDecimal secondNumber = new BigDecimal(eq.get(i+1));
                result = firstNumber.multiply(secondNumber);
                eq.set(i + 1,result.toString());
                eq.remove(i - 1);
                eq.remove(i - 1);
                i = i-1;
            }
        }

        for (int i = 1; i < eq.size(); i += 2) {
            if (i + 1 == eq.size()) {
                break;
            }
            else if (eq.get(i).charAt(0) == '-') {
                BigDecimal firstNumber = new BigDecimal(eq.get(i-1));
                BigDecimal secondNumber = new BigDecimal(eq.get(i+1));
                result = firstNumber.subtract(secondNumber);
                eq.set(i + 1, result.toString());
                eq.remove(i - 1);
                eq.remove(i - 1);
                i = i-1;
            }
        }

        for (int i = 1; i < eq.size(); i += 2) {
            if (i + 1 == eq.size()) {
                break;
            }
            else if (eq.get(i).charAt(0) == '+') {
                BigDecimal firstNumber = new BigDecimal(eq.get(i-1));
                BigDecimal secondNumber = new BigDecimal(eq.get(i+1));
                result = firstNumber.add(secondNumber);
                eq.set(i + 1, result.toString());
                eq.remove(i - 1);
                eq.remove(i - 1);
                i = i-1;
            }
        }

        return BigDecimal.valueOf(Double.parseDouble(eq.get(0)));
    }
}

