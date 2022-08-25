package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText text;
    TextView finalResult;
    Button c;
    Button del;
    Button multi;
    Button div;
    Button minus;
    Button plus;
    Button modulus;
    Button dot;
    Button equal;
    Button[] button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = new Button[11];
        button[0] = findViewById(R.id.button0);
        button[1] = findViewById(R.id.button1);
        button[2] = findViewById(R.id.button2);
        button[3] = findViewById(R.id.button3);
        button[4] = findViewById(R.id.button4);
        button[5] = findViewById(R.id.button5);
        button[6] = findViewById(R.id.button6);
        button[7] = findViewById(R.id.button7);
        button[8] = findViewById(R.id.button8);
        button[9] = findViewById(R.id.button9);
        button[10] = findViewById(R.id.button0);

        c = findViewById(R.id.button_c);
        del = findViewById(R.id.button_del);
        div = findViewById(R.id.button_division);
        minus = findViewById(R.id.button_minus);
        plus = findViewById(R.id.button_plus);
        modulus = findViewById(R.id.button_modlus);
        dot = findViewById(R.id.button_dot);
        multi = findViewById(R.id.button_multiply);
        equal = findViewById(R.id.button_equal);
        finalResult = findViewById(R.id.final_result);
        text = findViewById(R.id.display);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            text.setShowSoftInputOnFocus(false);
        }

    }

    public void onClick0(View view){
        text.setText(text.getText().toString() + 0);
    }

    public void onClick1(View view){
        text.setText(text.getText().toString() + 1);
    }

    public void onClick2(View view){
        text.setText(text.getText().toString() + 2);
    }

    public void onClick3(View view){
        text.setText(text.getText().toString() + 3);
    }

    public void onClick4(View view){
        text.setText(text.getText().toString() + 4);
    }

    public void onClick5(View view){
        text.setText(text.getText().toString() + 5);
    }

    public void onClick6(View view){
        text.setText(text.getText().toString() + 6);
    }

    public void onClick7(View view){
        text.setText(text.getText().toString() + 7);
    }

    public void onClick8(View view){
        text.setText(text.getText().toString() + 8);
    }

    public void onClick9(View view){
        text.setText(text.getText().toString() + 9);
    }

    public void onClickPlus(View view){
        if(!text.getText().toString().isEmpty()) {
            String tmp = text.getText().toString();
            if ((int) tmp.charAt(tmp.length() - 1) < 48 || (int) tmp.charAt(tmp.length() - 1) > 57)
                tmp = tmp.replace(tmp.charAt(tmp.length() - 1), '+');
            else
                tmp = tmp + '+';
            text.setText(tmp);
        }
    }

    public void onClickMinus(View view){
        if(!text.getText().toString().isEmpty()) {
            String tmp = text.getText().toString();
            if ((int) tmp.charAt(tmp.length() - 1) < 48 || (int) tmp.charAt(tmp.length() - 1) > 57)
                tmp = tmp.replace(tmp.charAt(tmp.length() - 1), '-');
            else
                tmp = tmp + '-';
            text.setText(tmp);
        }
    }

    public void onClickMulti(View view) {
        if(!text.getText().toString().isEmpty()) {
            String tmp = text.getText().toString();
            if ((int) tmp.charAt(tmp.length() - 1) < 48 || (int) tmp.charAt(tmp.length() - 1) > 57)
                tmp = tmp.replace(tmp.charAt(tmp.length() - 1), 'x');
            else
                tmp = tmp + 'x';
            text.setText(tmp);
        }
    }

    public void onClickDivide(View view){
        if(!text.getText().toString().isEmpty()) {
            String tmp = text.getText().toString();
            if ((int) tmp.charAt(tmp.length() - 1) < 48 || (int) tmp.charAt(tmp.length() - 1) > 57)
                tmp = tmp.replace(tmp.charAt(tmp.length() - 1), '/');
            else
                tmp = tmp + '/';
            text.setText(tmp);
        }
    }

    public void onClickMod(View view){
        if(!text.getText().toString().isEmpty()) {
            String tmp = text.getText().toString();
            if ((int) tmp.charAt(tmp.length() - 1) < 48 || (int) tmp.charAt(tmp.length() - 1) > 57)
                tmp = tmp.replace(tmp.charAt(tmp.length() - 1), '%');
            else
                tmp = tmp + '%';
            text.setText(tmp);
        }
    }

    public void onClickDot(View view){
        text.setText(String.format("%s.", text.getText().toString()));
    }

    public void onClickDel(View view){
        if(!text.getText().toString().isEmpty()) {
            String temp = text.getText().toString();
            text.setText(temp.substring(0, temp.length() - 1));
            finalResult.setText("");
        }
    }

    public void onClickC(View view){
        if(!text.getText().toString().isEmpty()) {
            text.setText("");
            finalResult.setText("");
        }
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void onClickEqual(View view){

        if(text.getText().toString().equals("911")){
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
        }
        else if(text.getText() != null){

            ArrayList<String> r = Parse.tokenize(text.getText().toString());

            if(!Validator.isValid(r)){
                text.setText("Error");
            }
            else {
                BigDecimal result = Calculator.calculate(r);
                finalResult.setText(result.toEngineeringString());
                System.out.println(result.toEngineeringString());
            }
        }
    }
}