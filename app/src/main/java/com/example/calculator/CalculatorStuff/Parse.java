package com.example.calculator;
import static com.example.calculator.Calculator.calculate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parse {

    private ArrayList<String> components = new ArrayList<>();

    public static ArrayList<String> tokenize(String text) {
        ArrayList<String> components = new ArrayList<>();
        Pattern pattern = Pattern.compile("(cos|sin|tan|log|ln|\\.e\\+)|" +
                "((^−)?\\d+(\\.\\d+)?)|" +
                "([+|\\−|/|^|!|e|÷|×|√|%|\\(||\\)])");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            components.add(matcher.group());
        }

        return components;
    }



    public static ArrayList<String> simplify(ArrayList<String> eq) {
        int start, end;


        while ((start = eq.indexOf("(")) != -1) {
            end = eq.indexOf(")");
            if (start + 1 == end) {
                eq.remove(start);
                eq.remove(start);
            } else if (start + 3 == end && eq.get(start + 1).equals("−")) {
                eq.set(start + 3, "−" + eq.get(start + 2));
                eq.remove(start);
                eq.remove(start);
                eq.remove(start);
            } else {
                List<String> temp = eq.subList(start + 1, end);
                try {
                    BigDecimal result = calculate(new ArrayList<>(temp));
                    eq.set(start, result.toString());
                    for (int i = end; i > start; i--) {
                        eq.remove(i);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        while ((start = eq.indexOf("|")) != -1) {
            eq.remove("|");
            end = eq.indexOf("|");
            if (start + 1 == end) {
                eq.remove(end);
            } else {
                List<String> temp = eq.subList(start, end);
                try {
                    BigDecimal result = calculate(new ArrayList<>(temp));
                    eq.set(start, result.abs().toString());
                    for (int i = end; i > start; i--) {
                        eq.remove(i);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return eq;
    }
}
