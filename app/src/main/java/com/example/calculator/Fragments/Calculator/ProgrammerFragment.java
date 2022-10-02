package com.example.calculator.Fragments;

import static android.graphics.Color.*;

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
import java.util.Locale;

public class ProgrammerFragment extends Fragment implements View.OnClickListener {

    private View view;
    private TextView binaryView;
    private TextView octalView;
    private TextView decimalView;
    private TextView hexadecimalView;
    private EditText text;
    private EditText binaryEditText;
    private EditText octalEditText;
    private EditText decimalEditText;
    private EditText hexaEditText;

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
    private int selected = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_programer_view, container, false);
        initializeVariables();
        text.setSelection(text.getText().length());
        binaryEditText.setSelection(binaryEditText.length());
        octalEditText.setSelection(octalEditText.length());
        decimalEditText.setSelection(decimalEditText.length());
        hexaEditText.setSelection(hexaEditText.length());
        onClick(view.findViewById(R.id.decimal_view));
        return view;
    }

    private void initializeVariables() {

        button = new Button[16];
        binaryView = view.findViewById(R.id.binary_view);
        octalView = view.findViewById(R.id.octal_view);
        decimalView = view.findViewById(R.id.decimal_view);
        hexadecimalView = view.findViewById(R.id.hexadecimal_view);
        binaryEditText = view.findViewById(R.id.binary_input);
        octalEditText = view.findViewById(R.id.octal_input);
        decimalEditText = view.findViewById(R.id.decimal_input);
        hexaEditText = view.findViewById(R.id.hexadecimal_input);

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
        button[10] = view.findViewById(R.id.button_A);
        button[11] = view.findViewById(R.id.button_B);
        button[12] = view.findViewById(R.id.button_C);
        button[13] = view.findViewById(R.id.button_D);
        button[14] = view.findViewById(R.id.button_E);
        button[15] = view.findViewById(R.id.button_F);

        c = view.findViewById(R.id.button_c);
        del = view.findViewById(R.id.button_del);
        div = view.findViewById(R.id.button_division);
        minus = view.findViewById(R.id.button_minus);
        plus = view.findViewById(R.id.button_plus);
        modulus = view.findViewById(R.id.button_modlus);
        dot = view.findViewById(R.id.button_dot);
        multi = view.findViewById(R.id.button_multiply);
        equal = view.findViewById(R.id.button_equal);
        text = view.findViewById(R.id.display);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            text.setShowSoftInputOnFocus(false);
            binaryEditText.setShowSoftInputOnFocus(false);
            octalEditText.setShowSoftInputOnFocus(false);
            decimalEditText.setShowSoftInputOnFocus(false);
            hexaEditText.setShowSoftInputOnFocus(false);
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
        button[10].setOnClickListener(this);
        button[11].setOnClickListener(this);
        button[12].setOnClickListener(this);
        button[13].setOnClickListener(this);
        button[14].setOnClickListener(this);
        button[15].setOnClickListener(this);
        c.setOnClickListener(this);
        del.setOnClickListener(this);
        div.setOnClickListener(this);
        dot.setOnClickListener(this);
        minus.setOnClickListener(this);
        modulus.setOnClickListener(this);
        multi.setOnClickListener(this);
        plus.setOnClickListener(this);
        equal.setOnClickListener(this);
        text.setOnClickListener(this);
        binaryView.setOnClickListener(this);
        octalView.setOnClickListener(this);
        decimalView.setOnClickListener(this);
        hexadecimalView.setOnClickListener(this);

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (text.getText().toString().isEmpty()) {
                    displayResult("0");
                }
                ArrayList<String> parsedText = Parse.tokenize(text.getText().toString());
                if (!text.getText().toString().isEmpty()) {
                    if (selected == 3) {
                        String result = null;
                        try {
                            if (Validator.isValid(text.getText().toString(), parsedText)) {
                                result = Calculator.calculate(parsedText).toString();
                            }
                        } catch (Exception e) {
                        }
                        if (result != null) {
                            displayResult(result);
                        }
                    } else {
                        displayResult(text.getText().toString());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        int position;

        if (!text.getText().toString().isEmpty() && text.getText().toString().equals("0")) {
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
                    text.setSelection(text.length());
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
                    text.setSelection(text.length());
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
                    text.setSelection(text.length());
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
                    text.setSelection(text.length());
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
                    text.setSelection(text.length());
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
                    text.setSelection(text.length());
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
                    text.setSelection(text.length());
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
                    text.setSelection(text.length());
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
                    text.setSelection(text.length());
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
                    text.setSelection(text.length());
                }
                break;

            case R.id.button_A:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%sA%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("A");
                    text.setSelection(text.length());
                }
                break;

            case R.id.button_B:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%sB%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("B");
                    text.setSelection(text.length());
                }
                break;

            case R.id.button_C:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%sC%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("C");
                    text.setSelection(text.length());
                }
                break;

            case R.id.button_D:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%sD%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("D");
                    text.setSelection(text.length());
                }
                break;

            case R.id.button_E:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%sE%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("E");
                    text.setSelection(text.length());
                }
                break;

            case R.id.button_F:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%sF%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("F");
                    text.setSelection(text.length());
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
                    text.setSelection(text.length());
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
                    text.setSelection(text.length());
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
                    text.setSelection(text.length());
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
                    text.setSelection(text.length());
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
                    text.setSelection(text.length());
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
                    text.setSelection(text.length());
                }
                break;


            case R.id.button_equal: {
                if (!text.getText().toString().isEmpty()) {

                    ArrayList<String> r = Parse.tokenize(text.getText().toString());

                    if (Validator.isValid(text.getText().toString(), r)) {
                        BigDecimal result = null;
                        try {
                            result = Calculator.calculate(r);
                        } catch (Exception e) {
                            text.setText("Error");
                        }
                        if (result != null && result.toString().charAt(result.toString().length() - 1) == '0' &&
                                result.toString().charAt(result.toString().length() - 2) == '.') {
                            text.setText(result.toBigInteger().toString());
                        } else if (result != null) {
                            text.setText(result.toEngineeringString());
                        }
                    }
                }
            }
            break;

            case R.id.button_c: {
                text.setText("0");
                binaryEditText.setText("");
                octalEditText.setText("");
                decimalEditText.setText("");
                hexaEditText.setText("");
            }
            break;

            case R.id.button_del: {

                if (!text.getText().toString().isEmpty()) {
                    position = text.getSelectionStart();
                    if (position != text.length() && position != 0) {
                        String currentText1 = text.getText().toString().substring(0, position - 1);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%s%s", currentText1, currentText2));
                        text.setSelection(position - 1);
                    } else {
                        String temp = text.getText().toString();
                        text.setText(temp.substring(0, temp.length() - 1));
                        text.setSelection(text.length());
                    }
                }
            }
            break;

            case R.id.binary_view: {
                if (selected != 1) {
                    selected = 1;
                    text.setText("0");
                    binaryView.setTextColor(GRAY);
                    octalView.setTextColor(parseColor("#D5C9C9"));
                    decimalView.setTextColor(parseColor("#D5C9C9"));
                    hexadecimalView.setTextColor(parseColor("#D5C9C9"));
                    div.setTextColor(GRAY);
                    minus.setTextColor(GRAY);
                    plus.setTextColor(GRAY);
                    modulus.setTextColor(GRAY);
                    dot.setTextColor(GRAY);
                    multi.setTextColor(GRAY);
                    equal.setTextColor(GRAY);
                    div.setClickable(false);
                    minus.setClickable(false);
                    plus.setClickable(false);
                    modulus.setClickable(false);
                    dot.setClickable(false);
                    multi.setClickable(false);
                    equal.setClickable(false);

                    for (int i = 2; i < 16; i++) {
                        button[i].setClickable(false);
                        button[i].setTextColor(DKGRAY);
                    }
                }
            }
            break;

            case R.id.octal_view: {
                if (selected != 2) {
                    selected = 2;
                    text.setText("0");
                    octalView.setTextColor(GRAY);
                    binaryView.setTextColor(parseColor("#D5C9C9"));
                    decimalView.setTextColor(parseColor("#D5C9C9"));
                    hexadecimalView.setTextColor(parseColor("#D5C9C9"));
                    div.setTextColor(GRAY);
                    minus.setTextColor(GRAY);
                    plus.setTextColor(GRAY);
                    modulus.setTextColor(GRAY);
                    dot.setTextColor(GRAY);
                    multi.setTextColor(GRAY);
                    equal.setTextColor(GRAY);

                    div.setClickable(false);
                    minus.setClickable(false);
                    plus.setClickable(false);
                    modulus.setClickable(false);
                    dot.setClickable(false);
                    multi.setClickable(false);
                    equal.setClickable(false);

                    for (int i = 0; i < 8; i++) {
                        button[i].setClickable(true);
                        button[i].setTextColor(parseColor("#D5C9C9"));
                    }
                    for (int i = 8; i < 16; i++) {
                        button[i].setClickable(false);
                        button[i].setTextColor(DKGRAY);
                    }
                }
            }
            break;

            case R.id.decimal_view: {
                if (selected != 3) {
                    selected = 3;
                    text.setText("0");
                    decimalView.setTextColor(GRAY);
                    hexadecimalView.setTextColor(parseColor("#D5C9C9"));
                    binaryView.setTextColor(parseColor("#D5C9C9"));
                    octalView.setTextColor(parseColor("#D5C9C9"));
                    div.setTextColor(parseColor("#D5C9C9"));
                    minus.setTextColor(parseColor("#D5C9C9"));
                    plus.setTextColor(parseColor("#D5C9C9"));
                    modulus.setTextColor(parseColor("#D5C9C9"));
                    dot.setTextColor(parseColor("#D5C9C9"));
                    multi.setTextColor(parseColor("#D5C9C9"));
                    equal.setTextColor(parseColor("#D5C9C9"));

                    for (int i = 0; i < 10; i++) {
                        button[i].setClickable(true);
                        button[i].setTextColor(parseColor("#D5C9C9"));
                    }
                    for (int i = 10; i < 16; i++) {
                        button[i].setClickable(false);
                        button[i].setTextColor(DKGRAY);
                    }
                }
            }
            break;

            case R.id.hexadecimal_view: {
                if (selected != 4) {
                    selected = 4;
                    text.setText("0");
                    binaryView.setTextColor(parseColor("#D5C9C9"));
                    decimalView.setTextColor(parseColor("#D5C9C9"));
                    octalView.setTextColor(parseColor("#D5C9C9"));
                    hexadecimalView.setTextColor(GRAY);
                    div.setTextColor(GRAY);
                    minus.setTextColor(GRAY);
                    plus.setTextColor(GRAY);
                    modulus.setTextColor(GRAY);
                    dot.setTextColor(GRAY);
                    multi.setTextColor(GRAY);
                    equal.setTextColor(GRAY);

                    div.setClickable(false);
                    minus.setClickable(false);
                    plus.setClickable(false);
                    modulus.setClickable(false);
                    dot.setClickable(false);
                    multi.setClickable(false);
                    equal.setClickable(false);
                    for (int i = 0; i < 16; i++) {
                        button[i].setClickable(true);
                        button[i].setTextColor(parseColor("#D5C9C9"));
                    }
                }
            }
            break;
        }
    }

    int getDecimal(String result) {
        switch (selected) {
            case 1:
                return Integer.parseInt(String.valueOf((int) Double.parseDouble(result)), 2);
            case 2:
                return Integer.parseInt(String.valueOf((int) Double.parseDouble(result)), 8);
            case 3:
                return Integer.parseInt(result);
            case 4:
                return Integer.parseInt(result, 16);
        }
        return 0;
    }

    void displayResult(String result) {
        switch (selected) {
            case 1:
                int decimal = Integer.parseInt(String.valueOf((int) Double.parseDouble(result)), 2);
                binaryEditText.setText(String.valueOf((int) Double.parseDouble(result)));
                octalEditText.setText(Integer.toOctalString(decimal));
                decimalEditText.setText(String.format("%d ", decimal));
                hexaEditText.setText(Integer.toHexString((int) Double.parseDouble(String.valueOf(decimal))).toUpperCase(Locale.ROOT));
                break;
            case 2:
                int decimal2 = Integer.parseInt(String.valueOf((int) Double.parseDouble(result)), 8);
                octalEditText.setText(String.valueOf((int) Double.parseDouble(result)));
                decimalEditText.setText(String.format("%d ", decimal2));
                binaryEditText.setText(Integer.toBinaryString(decimal2));
                hexaEditText.setText(Integer.toHexString(decimal2).toUpperCase(Locale.ROOT));
                break;
            case 3:
                decimalEditText.setText(String.format("%d ", (int) Double.parseDouble(result)));
                binaryEditText.setText(Integer.toBinaryString((int) Double.parseDouble(result)));
                octalEditText.setText(Integer.toOctalString((int) Double.parseDouble(result)));
                hexaEditText.setText(Integer.toHexString((int) Double.parseDouble(result)).toUpperCase(Locale.ROOT));
                break;
            case 4:
                int decimal4 = Integer.parseInt(result, 16);
                hexaEditText.setText(String.valueOf(result));
                binaryEditText.setText(Integer.toBinaryString(decimal4));
                decimalEditText.setText(String.format("%d ", decimal4));
                octalEditText.setText(Integer.toOctalString(decimal4));
                break;
        }
    }
}