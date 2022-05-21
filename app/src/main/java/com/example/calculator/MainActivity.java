package com.example.calculator;

import static com.example.calculator.Parse.tokenize;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {

    TextView result, text;
    Button button[];
    Button c, del, multi, div, minus, plus, modulus, dot, equal;
    private String number1 = "Null", number2 = "Null";
    private char sign = 'N';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.textView);
        text = findViewById(R.id.screenShow);
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

    }

    @Override
    protected void onStart() {
        Toast.makeText(getApplicationContext(),"Hello ", Toast.LENGTH_SHORT);
        super.onStart();
    }

    @Override
    protected void onStop() {
        Toast.makeText(getApplicationContext(),"Waiting ..!", Toast.LENGTH_SHORT);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(getApplicationContext(),"Bye Gonna Miss You", Toast.LENGTH_LONG);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Toast.makeText(getApplicationContext(),"Waiting For You", Toast.LENGTH_SHORT);
        super.onPause();
    }

    @Override
    protected void onResume() {
        Toast.makeText(getApplicationContext(),"Welcome Back", Toast.LENGTH_SHORT);
        super.onResume();
    }

    public void onClick0(View view){
        text.setText(text.getText().toString() + 0 + "");
    }

    public void onClick1(View view){
        text.setText(text.getText().toString() + 1 + "");
    }

    public void onClick2(View view){
        text.setText(text.getText().toString() + 2 + "");
    }

    public void onClick3(View view){
        text.setText(text.getText().toString() + 3 + "");
    }

    public void onClick4(View view){
        text.setText(text.getText().toString() + 4 + "");
    }

    public void onClick5(View view){
        text.setText(text.getText().toString() + 5 + "");
    }

    public void onClick6(View view){
        text.setText(text.getText().toString() + 6 + "");
    }

    public void onClick7(View view){
        text.setText(text.getText().toString() + 7 + "");
    }

    public void onClick8(View view){
        text.setText(text.getText().toString() + 8 + "");
    }

    public void onClick9(View view){
        text.setText(text.getText().toString() + 9 + "");
    }

    public void onClickPlus(View view){
        text.setText(text.getText().toString() + "+");
    }

    public void onClickMinus(View view){
        text.setText(text.getText().toString() + "-");
    }

    public void onClickMulti(View view) {
        text.setText(text.getText().toString() + "x");
    }

    public void onClickDivide(View view){
        text.setText(text.getText().toString() + "/");
    }

    public void onClickMod(View view){
        text.setText(text.getText().toString() + "%");
    }

    public void onClickDot(View view){
        text.setText(text.getText().toString() + ".");
    }

    public void onClickDel(View view){
        String temp = text.getText().toString();
        text.setText(temp.substring(0,temp.length()-1));
    }

    public void onClickEqual(View view){

        if(text.getText().toString().equals("911")){

            startActivity(new Intent(MainActivity.this, MainActivity2.class));
        }
        else{

            ArrayList<String> r = Parse.tokenize(text.getText().toString());
            double result = Calculator.calculate(r);

            if(result == 0.0000E11010){
                text.setText("Error");
            }
            else {
                int result2 = (int) result;
                if (result - (double) result2 == 0.0) {
                    System.out.println("result : " + result);
                    text.setText(result2 + "");
                } else {
                    String s = String.format("%.6f", result);
                    text.setText(s);
                }
            }
        }
    }

    public void onClickC(View view){
        text.setText("");
        result.setText("");
    }
}