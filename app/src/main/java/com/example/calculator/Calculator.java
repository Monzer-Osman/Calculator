package com.example.calculator;
import android.util.Log;

import java.util.ArrayList;

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

    public static double calculate(ArrayList<String> eq) {

        double result;

        for (int i = 1; i < eq.size(); i += 1) {

            if (i + 1 == eq.size()) {
                break;
            } else if (eq.get(i).charAt(0) == '%') {
                result = Double.parseDouble(eq.get(i - 1)) % Double.parseDouble(eq.get(i + 1));
                eq.set(i + 1, String.valueOf(result));
                eq.remove(i - 1);
                eq.remove(i - 1);
            }
        }

        for (int i = 1; i < eq.size(); i += 1) {

            if (i + 1 == eq.size()) {
                break;
            }
            else if (eq.get(i).charAt(0) == '/') {
                result = Double.parseDouble(eq.get(i - 1)) / Double.parseDouble(eq.get(i + 1));
                eq.set(i + 1, String.valueOf(result));
                eq.remove(i - 1);
                eq.remove(i - 1);
            }
        }

        for (int i = 1; i < eq.size(); i += 1) {

            if (i + 1 == eq.size()) {
                break;
            }
            else if (eq.get(i).charAt(0) == 'x') {
                result = Double.parseDouble(eq.get(i - 1)) * Double.parseDouble(eq.get(i + 1));
                eq.set(i + 1, String.valueOf(result));
                eq.remove(i - 1);
                eq.remove(i - 1);
            }
        }
        //Log.d("eq : ", eq.toString());

        for (int i = 1; i < eq.size(); i += 2) {
            if (i + 1 == eq.size()) {
                break;
            }
            else if (eq.get(i).charAt(0) == '-') {
                result = Double.parseDouble(eq.get(i-1)) - Double.parseDouble(eq.get(i + 1));
                eq.set(i + 1, String.valueOf(result));
            }
        }

        for (int i = 1; i < eq.size(); i += 2) {
            if (i + 1 == eq.size()) {
                break;
            }
            else if (eq.get(i).charAt(0) == '+') {
                result = Double.parseDouble(eq.get(i-1)) + Double.parseDouble(eq.get(i + 1));
                eq.set(i + 1, String.valueOf(result));
            }
        }

        return Double.valueOf(eq.get(eq.size()-1));
    }
}

