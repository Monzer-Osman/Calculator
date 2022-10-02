package com.example.calculator;

import java.math.BigDecimal;

import java.util.ArrayList;

public class Calculator {

    public static BigDecimal calculate(ArrayList<String> eq) throws Exception{

        BigDecimal result;

        for (int i = 0; i < eq.size(); i += 1) {

            if (i + 1 == eq.size()) {
                break;

            } else if (eq.get(i).charAt(0) == 'e') {
                if (eq.get(i + 1).equals("^")) {
                    BigDecimal firstNumber = new BigDecimal(eq.get(i + 2));
                    result = BigDecimal.valueOf(Math.exp(firstNumber.doubleValue()));
                    eq.set(i + 2, result.toString());
                    eq.remove(i);
                    eq.remove(i);
                    i = i - 1;
                } else {
                    throw new Exception();
                }
            }
        }

        for (int i = 0; i < eq.size(); i += 1) {

            if (i + 1 == eq.size()) {
                break;
            } else if (eq.get(i).equals("cos")) {
                if (i + 3 < eq.size() && eq.get(i + 2).equals("^") && eq.get(i + 3).equals("−1")) {
                    BigDecimal firstNumber = new BigDecimal(eq.get(i + 1));
                    result = BigDecimal.valueOf(Math.acos(firstNumber.doubleValue()));
                    eq.set(i + 3, result.toString());
                    eq.remove(i);
                    eq.remove(i);
                    eq.remove(i);
                } else {
                    BigDecimal firstNumber = new BigDecimal(eq.get(i + 1));
                    result = BigDecimal.valueOf(Math.cos(firstNumber.doubleValue()));
                    eq.set(i + 1, result.toString());
                    eq.remove(i);
                }
                i = i - 1;
            } else if (eq.get(i).equals("sin")) {
                if (i + 3 < eq.size() && eq.get(i + 2).equals("^") && eq.get(i + 3).equals("−1")) {

                    BigDecimal firstNumber = new BigDecimal(eq.get(i + 1));
                    result = BigDecimal.valueOf(Math.asin(firstNumber.doubleValue()));
                    eq.set(i + 3, result.toString());
                    eq.remove(i);
                    eq.remove(i);
                    eq.remove(i);
                } else {
                    BigDecimal firstNumber = new BigDecimal(eq.get(i + 1));
                    result = BigDecimal.valueOf(Math.sin(firstNumber.doubleValue()));
                    eq.set(i + 1, result.toString());
                    eq.remove(i);
                }
                i = i - 1;
            } else if (eq.get(i).equals("tan")) {
                if (i + 3 < eq.size() && eq.get(i + 2).equals("^") && eq.get(i + 3).equals("−1")) {
                    BigDecimal firstNumber = new BigDecimal(eq.get(i + 1));
                    result = BigDecimal.valueOf(Math.atan(firstNumber.doubleValue()));
                    eq.set(i + 3, result.toString());
                    eq.remove(i);
                    eq.remove(i);
                    eq.remove(i);

                } else {
                    BigDecimal firstNumber = new BigDecimal(eq.get(i + 1));
                    result = BigDecimal.valueOf(Math.tan(firstNumber.doubleValue()));
                    eq.set(i + 1, result.toString());
                    eq.remove(i);
                }
                i = i - 1;
            } else if (eq.get(i).equals("log")) {
                BigDecimal firstNumber = new BigDecimal(eq.get(i + 1));
                result = BigDecimal.valueOf(Math.log10(firstNumber.doubleValue()));
                eq.set(i + 1, result.toString());
                eq.remove(i);
                i = i - 1;
            } else if (eq.get(i).equals("ln")) {
                BigDecimal firstNumber = new BigDecimal(eq.get(i + 1));
                result = BigDecimal.valueOf(Math.log(firstNumber.doubleValue()));
                eq.set(i + 1, result.toString());
                eq.remove(i);
                i = i - 1;
            }
        }

        for (int i = 1; i < eq.size(); i += 1) {

            if (i + 1 == eq.size()) {
                break;
            } else if (eq.get(i).equals("^")) {
                BigDecimal firstNumber = new BigDecimal(eq.get(i - 1));
                BigDecimal secondNumber = new BigDecimal(eq.get(i + 1));
                result = new BigDecimal(String.valueOf(Math.pow(Double.parseDouble(firstNumber.toString()),
                        Double.parseDouble(secondNumber.toString()))));
                eq.set(i + 1, result.toString());
                eq.remove(i - 1);
                eq.remove(i - 1);
                i = i - 1;
            }
        }

        for (int i = 1; i < eq.size(); i += 1) {

            if (i + 1 == eq.size()) {
                break;
            } else if (eq.get(i).charAt(0) == '%') {
                BigDecimal firstNumber = new BigDecimal(eq.get(i - 1));
                BigDecimal secondNumber = new BigDecimal(eq.get(i + 1));
                if (!secondNumber.equals(BigDecimal.ZERO)) {
                    result = firstNumber.remainder(secondNumber);
                    eq.set(i + 1, result.toString());
                }
                eq.remove(i - 1);
                eq.remove(i - 1);
                i = i - 1;
            }
        }

        for (int i = 1; i < eq.size(); i += 1) {

            if (i + 1 == eq.size()) {
                break;
            } else if (eq.get(i).charAt(0) == '÷' || eq.get(i).charAt(0) == '/') {

                BigDecimal firstNumber = new BigDecimal(eq.get(i - 1));
                BigDecimal secondNumber = new BigDecimal(eq.get(i + 1));
                if (!secondNumber.equals(BigDecimal.ZERO)) {
                    result = new BigDecimal(firstNumber.doubleValue() / secondNumber.doubleValue());
                    eq.set(i + 1, result.toString());
                }else{
                    throw new Exception();
                }
                eq.remove(i - 1);
                eq.remove(i - 1);
                i = i - 1;
            }
        }

        for (int i = 1; i < eq.size(); i += 1) {

            if (i + 1 == eq.size()) {
                break;
            } else if (eq.get(i).charAt(0) == '×') {
                BigDecimal firstNumber = new BigDecimal(eq.get(i - 1));
                BigDecimal secondNumber = new BigDecimal(eq.get(i + 1));
                result = firstNumber.multiply(secondNumber);
                eq.set(i + 1, result.toString());
                eq.remove(i - 1);
                eq.remove(i - 1);
                i = i - 1;
            }
        }

        for (int i = 1; i < eq.size(); i += 1) {
            if (i + 1 == eq.size()) {
                break;
            } else if (eq.get(i).equals("−")) {
                BigDecimal firstNumber = new BigDecimal(eq.get(i - 1));
                BigDecimal secondNumber = new BigDecimal(eq.get(i + 1));
                result = firstNumber.subtract(secondNumber);
                eq.set(i + 1, result.toString());
                eq.remove(i - 1);
                eq.remove(i - 1);
                i = i - 1;
            }
        }

        for (int i = 1; i < eq.size(); i += 1) {
            if (i + 1 == eq.size()) {
                break;
            } else if (eq.get(i).charAt(0) == '+') {
                BigDecimal firstNumber = new BigDecimal(eq.get(i - 1));
                BigDecimal secondNumber = new BigDecimal(eq.get(i + 1));
                result = firstNumber.add(secondNumber);
                eq.set(i + 1, result.toString());
                eq.remove(i - 1);
                eq.remove(i - 1);
                i = i - 1;
            }
        }

        for (int i = 0; i < eq.size(); i += 1) {
            if (i + 1 == eq.size()) {
                break;
            } else if (eq.get(i).equals("√")) {
                BigDecimal firstNumber = new BigDecimal(eq.get(i - 1));
                BigDecimal secondNumber = new BigDecimal(eq.get(i + 1));
                if (firstNumber.equals(new BigDecimal(2))) {
                    result = BigDecimal.valueOf(Math.sqrt(secondNumber.doubleValue()));
                    eq.set(i + 1, result.toString());
                    eq.remove(i - 1);
                    eq.remove(i - 1);
                    i = i - 1;
                } else if (firstNumber.equals(new BigDecimal(3))) {
                    result = BigDecimal.valueOf(Math.cbrt(secondNumber.doubleValue()));
                    eq.set(i + 1, result.toString());
                    eq.remove(i - 1);
                    eq.remove(i - 1);
                    i = i - 1;
                }
            }
        }

        for (int i = 1; i < eq.size(); i += 1) {
            if (i - 1 == -1) {
                break;
            } else if (eq.get(i).equals("!")) {
                long firstNumber = Long.parseLong(eq.get(i - 1));
                result = BigDecimal.valueOf(factorial(firstNumber));
                eq.set(i, result.toString());
                eq.remove(i - 1);
                i = i - 1;
            }
        }
        System.out.println("the equation is " +  eq.toString());
        System.out.println(eq);
        return BigDecimal.valueOf(Double.parseDouble(eq.get(0)));
    }

    static long factorial(long number){
        long fact = number;
        for(long i = number-1; i > 1; i--){
            fact *= i;
        }

        return fact;
    }
}

