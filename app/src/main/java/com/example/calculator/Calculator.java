package com.example.calculator;

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

        int n = eq.size();

        for(int i = 2; i < n; i+=2) {
            if (eq.get(i).charAt(0) < 48 || eq.get(0).charAt(0) > 56) {
                return 0.0000E11010;
            }
        }

        double result = 0.0;

        if(eq.get(0).charAt(0) == '-'){
            result = -1 * Double.parseDouble(eq.get(0));
        }
        else if(eq.get(0).charAt(0) >= 48 && eq.get(0).charAt(0) <= 56){
            result = Double.parseDouble(eq.get(0));
        }
        else{
            return 0.0000E11010;
        }

        for (int i = 1; i < n; i += 2)
        {
            if (eq.get(i).charAt(0) == '+')
            {
                result += Double.parseDouble(eq.get(i+1));
            }
            else if (eq.get(i).charAt(0) == '-')
            {
                result -= Double.parseDouble(eq.get(i+1));
            }
            else if (eq.get(i).charAt(0) == 'x'){
                result *= Double.parseDouble(eq.get(i+1));
            }
            else if (eq.get(i).charAt(0) == '/'){
                result /= Double.parseDouble(eq.get(i+1));
            }
            else if (eq.get(i).charAt(0) == '%') {
                result %= Double.parseDouble(eq.get(i+1));
            }
        }

        return result;
    }
}
