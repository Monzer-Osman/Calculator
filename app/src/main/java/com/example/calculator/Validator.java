package com.example.calculator;

import java.util.ArrayList;

public class Validator {
    
    public static boolean isValid(ArrayList<String> equation){

        for(int i = 2; i < equation.size(); i+=2) {
            if ((int)equation.get(i).charAt(0) < 48 || (int)equation.get(i).charAt(0) > 57) {
                return false;
            }
        }

        if(equation.get(0).charAt(0) == '-'){
            equation.add(0, "-" + equation.get(1));
            equation.remove(1);
            return true;
        }
        else if(equation.get(0).charAt(0) >= 48 && equation.get(0).charAt(0) <= 57){
            return true;
        }
        else{
            return false;
        }
    }
}
