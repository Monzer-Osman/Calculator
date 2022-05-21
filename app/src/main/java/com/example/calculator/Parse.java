package com.example.calculator;
import java.util.ArrayList;

public class Parse {

    private ArrayList<String> components = new ArrayList<>();

    protected static ArrayList<String> tokenize(String text){

        ArrayList<String> components = new ArrayList<>();
        String tmp = "";

        for(int i = 0; i < text.length(); i++)
        {
            if((int)text.charAt(i) < 48 && text.charAt(i) != '.' || text.charAt(i) == 'x')
            {
                if(!tmp.isEmpty())
                    components.add(tmp);

                components.add(text.charAt(i)+"");
                tmp = "";
            }
            else
            {
                tmp += text.charAt(i);
            }
        }

        components.add(tmp);
        return components;
    }

//    public ArrayList<String> Order(ArrayList<String> data){
//
//    }
}
