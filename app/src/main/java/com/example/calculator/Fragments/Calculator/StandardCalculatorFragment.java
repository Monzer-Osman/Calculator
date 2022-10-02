package com.example.calculator.Fragments.Calculator;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculator.CalculatorStuff.Calculator;
import com.example.calculator.CalculatorStuff.Parse;
import com.example.calculator.R;
import com.example.calculator.CalculatorStuff.Validator;

import java.math.BigDecimal;
import java.util.ArrayList;


public class StandardCalculatorFragment extends Fragment implements View.OnClickListener {

    private View view;
    private EditText text;
    private TextView finalResult;
    private TextView autoSize;
    private Button c;
    private Button del;
    private Button multi;
    private Button div;
    private Button minus;
    private Button plus;
    private Button modulus;
    private Button dot;
    private Button equal;
    private Button[] button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_standard_calculator, container, false);
        view.setOnClickListener(this);

        initializeVariables();

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(text.getText().toString().isEmpty()){
                    finalResult.setText("0");
                }

                if (!text.getText().toString().isEmpty()) {
                    ArrayList<String> r = new ArrayList<>();
                    try {
                        r = Parse.tokenize(text.getText().toString());
                    }catch (Exception e){
                        finalResult.setText("Error");
                    }
                    if (r.size() >= 3) {
                        if (!Validator.isValid(text.getText().toString(), r)) {
                            finalResult.setText("Error");
                        } else {
                            BigDecimal result = null;
                            try {
                                result = Calculator.calculate(r);
                            } catch (Exception e) {
                                finalResult.setText("Error");
                            }
                            if (result != null) {
                                if (result.toString().charAt(result.toString().length() - 1) == '0' &&
                                        result.toString().charAt(result.toString().length() - 2) == '.') {
                                    finalResult.setText(result.toBigInteger().toString());
                                } else {
                                    finalResult.setText(result.toEngineeringString());
                                }
                            }
                        }
                    }
                }

                autoSize.setText(charSequence.toString());
                float size = autoSize.getTextSize();
                size = size / 3;
                System.out.println("text size: " + size);
                text.setTextSize(size);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return view;
    }

    private void initializeVariables() {

        button = new Button[11];

        button[0] = view.findViewById(R.id.button_0);
        button[1] = view.findViewById(R.id.button_1);
        button[2] = view.findViewById(R.id.button_2);
        button[3] = view.findViewById(R.id.button_3);
        button[4] = view.findViewById(R.id.button_4);
        button[5] = view.findViewById(R.id.button_5);
        button[6] = view.findViewById(R.id.button_6);
        button[7] = view.findViewById(R.id.button_7);
        button[8] = view.findViewById(R.id.button_8);
        button[9] = view.findViewById(R.id.button_9);
        button[10] = view.findViewById(R.id.button_0);

        c = view.findViewById(R.id.button_c);
        del = view.findViewById(R.id.button_del);
        div = view.findViewById(R.id.button_division);
        minus = view.findViewById(R.id.button_minus);
        plus = view.findViewById(R.id.button_plus);
        modulus = view.findViewById(R.id.button_modlus);
        dot = view.findViewById(R.id.button_dot);
        multi = view.findViewById(R.id.button_multiply);
        equal = view.findViewById(R.id.button_equal);
        finalResult = view.findViewById(R.id.final_result);
        autoSize = view.findViewById(R.id.autoSize);
        text = view.findViewById(R.id.display);
        text.setText("0");
        text.setSelection(text.length());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            text.setShowSoftInputOnFocus(false);
        }

        button[0].setOnClickListener(this);
        button[1].setOnClickListener(this);
        button[2].setOnClickListener(this);
        button[3].setOnClickListener(this);
        button[4].setOnClickListener(this);
        button[5].setOnClickListener(this);
        button[6].setOnClickListener(this);
        button[7].setOnClickListener(this);
        button[8].setOnClickListener(this);
        button[9].setOnClickListener(this);
        c.setOnClickListener(this);
        del.setOnClickListener(this);
        div.setOnClickListener(this);
        dot.setOnClickListener(this);
        minus.setOnClickListener(this);
        modulus.setOnClickListener(this);
        multi.setOnClickListener(this);
        plus.setOnClickListener(this);
        equal.setOnClickListener(this);
        finalResult.setOnClickListener(this);
        text.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int position;
        if(text.getText().toString().equals("0") && view.getId() != R.id.button_dot){
            text.setText("");
        }

        switch (view.getId()) {
            case R.id.button_0:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s0%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("0");
                }
                break;

            case R.id.button_1:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s1%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("1");
                }
                break;

            case R.id.button_2:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s2%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("2");
                }
                break;

            case R.id.button_3:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s3%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("3");
                }
                break;

            case R.id.button_4:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s4%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("4");
                }
                break;

            case R.id.button_5:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s5%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("5");
                }
                break;

            case R.id.button_6:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s6%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("6");
                }
                break;

            case R.id.button_7:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s7%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("7");
                }
                break;

            case R.id.button_8:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s8%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("8");
                }
                break;

            case R.id.button_9:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s9%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("9");
                }
                break;

            case R.id.button_dot:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s.%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append(".");
                }
                break;

            case R.id.button_plus:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s+%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("+");
                }
                break;

            case R.id.button_minus:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s−%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("−");
                }
                break;

            case R.id.button_multiply:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s×%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("×");
                }
                break;

            case R.id.button_division:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s÷%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("÷");
                }
                break;

            case R.id.button_modlus:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s%%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("%");
                }
                break;


            case R.id.button_equal: {
                if (!finalResult.getText().toString().isEmpty()) {
                    text.setText(finalResult.getText());
                    text.setSelection(text.getText().length());
                    finalResult.setText("");
                }
            }
            break;

            case R.id.button_c: {
                text.setText("");
                finalResult.setText("");
            }
            break;

            case R.id.button_del: {

                if (!text.getText().toString().isEmpty()) {
                    position = text.getSelectionStart();
                    if (position != text.length() && position != 0) {
                        String currentText1 = text.getText().toString().substring(0, position - 1);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%s%s", currentText1, currentText2));
                        text.setSelection(position-1);
                    } else {
                        String temp = text.getText().toString();
                        text.setText(temp.substring(0, temp.length() - 1));
                        text.setSelection(text.length());
                    }
                }
            }
            break;
        }
    }
}