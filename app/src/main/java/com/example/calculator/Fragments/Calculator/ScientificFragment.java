package com.example.calculator.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.calculator.CalculatorStuff.Calculator;
import com.example.calculator.CalculatorStuff.Parse;
import com.example.calculator.R;
import com.example.calculator.CalculatorStuff.Validator;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ScientificFragment extends Fragment implements View.OnClickListener {

    View view;
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
    Button secondDegreeButton;
    Button xPower2;
    Button xPowerN;
    Button squareRoot;
    Button rand;
    Button oneDividedByX;
    Button inverse;
    Button absValue;
    Button openParanthesis;
    Button closedParanthesis;
    Button piButton;
    Button cosButton;
    Button sinButton;
    Button tanButton;
    Button lnButton;
    Button logButton;
    Button eButton;
    Button factorialButton;
    Button expButton;
    Button equal;
    Button[] button;
    boolean invOn = false;
    boolean secondDegree = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_scientific_calculator, container, false);
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
                    if(r.size() != 0){
                        if(r.size() >= 3 || r.get(r.size()-1).equals("!")) {
                            if (!Validator.isValid(text.getText().toString(), r)) {
                                finalResult.setText("Error");
                                Log.d("the error is from", r.toString());
                            } else {
                                BigDecimal result = null;
                                try {
                                    r = Parse.simplify(r);
                                    result = Calculator.calculate(r);
                                } catch (Exception e) {
                                    Log.d("2-the error is from", r.toString());
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
                }
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
        secondDegreeButton = view.findViewById(R.id.button_2_nd);
        secondDegreeButton.setText(Html.fromHtml("2<sup><small>nd</small></sup>"));
        xPower2 = view.findViewById(R.id.button_pow_2);
        xPower2.setText(Html.fromHtml("X<sup><small>2</small></sup>"));
        xPowerN = view.findViewById(R.id.button_pow_n);
        xPowerN.setText(Html.fromHtml("X<sup>n</sup>"));
        squareRoot = view.findViewById(R.id.button_squareRoot_2);
        squareRoot.setText(Html.fromHtml("<sup><small>2</small></sup>√X"));
        rand = view.findViewById(R.id.button_rand);
        oneDividedByX = view.findViewById(R.id.button_1_div_x);
        oneDividedByX.setText(Html.fromHtml("1/x")); //"1<br>―<br>"
        inverse = view.findViewById(R.id.button_inv);
        absValue = view.findViewById(R.id.button_abs);
        openParanthesis = view.findViewById(R.id.button_open_Parenthesis);
        closedParanthesis = view.findViewById(R.id.button_close_Parenthesis);
        piButton = view.findViewById(R.id.button_pi);
        cosButton = view.findViewById(R.id.button_cos);
        sinButton = view.findViewById(R.id.button_sin);
        tanButton = view.findViewById(R.id.button_tan);
        lnButton = view.findViewById(R.id.button_ln);
        logButton = view.findViewById(R.id.button_log);
        eButton = view.findViewById(R.id.button_e);
        factorialButton = view.findViewById(R.id.button_x_fact);
        expButton = view.findViewById(R.id.button_exp);
        equal = view.findViewById(R.id.button_equal);
        finalResult = view.findViewById(R.id.final_result);
        text = view.findViewById(R.id.display);
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
        secondDegreeButton.setOnClickListener(this);
        xPower2.setOnClickListener(this);
        xPowerN.setOnClickListener(this);
        squareRoot.setOnClickListener(this);
        rand.setOnClickListener(this);
        oneDividedByX.setOnClickListener(this);
        inverse.setOnClickListener(this);
        absValue.setOnClickListener(this);
        openParanthesis.setOnClickListener(this);
        closedParanthesis.setOnClickListener(this);
        piButton.setOnClickListener(this);
        cosButton.setOnClickListener(this);
        sinButton.setOnClickListener(this);
        tanButton.setOnClickListener(this);
        lnButton.setOnClickListener(this);
        logButton.setOnClickListener(this);
        eButton.setOnClickListener(this);
        factorialButton.setOnClickListener(this);
        expButton.setOnClickListener(this);
        equal.setOnClickListener(this);
        text.setOnClickListener(this);
        finalResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (text.getText().toString().equals("0") && view.getId() != R.id.button_dot) {
            text.setText("");
        }
        int position = text.getSelectionStart();
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
                    text.setSelection(text.length());
                }
                break;

            case R.id.button_2_nd:
                if (secondDegree) {
                    xPower2.setText(Html.fromHtml("X<sup><small>3</small></sup>"));
                    squareRoot.setText(Html.fromHtml("<sup><small>3</small></sup>√X"));
                    secondDegree = false;
                } else {
                    xPower2.setText(Html.fromHtml("X<sup><small>2</small></sup>"));
                    squareRoot.setText(Html.fromHtml("<sup><small>2</small></sup>√X"));
                    secondDegree = true;
                }
                break;

            case R.id.button_pow_2:
                position = text.getSelectionStart();
                if (secondDegree) {
                    if (position != text.length()) {
                        String currentText1 = text.getText().toString().substring(0, position);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%s^(2)%s", currentText1, currentText2));
                        text.setSelection(position + 4);
                    } else {
                        text.append("^(2)");
                    }
                } else {
                    if (position != text.length()) {
                        String currentText1 = text.getText().toString().substring(0, position);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%s^(3)%s", currentText1, currentText2));
                        text.setSelection(position + 4);
                    } else {
                        text.append("^(3)");
                    }
                }

                break;

            case R.id.button_pow_n:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s^(%s", currentText1, currentText2));
                    text.setSelection(position + 2);
                } else {
                    text.append("^(");
                }
                break;

            case R.id.button_squareRoot_2:
                position = text.getSelectionStart();
                if (secondDegree) {
                    if (position != text.length()) {
                        String currentText1 = text.getText().toString().substring(0, position);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%s2√%s", currentText1, currentText2));
                        text.setSelection(position + 2);
                    } else {
                        text.append("2√");
                    }
                } else {
                    if (position != text.length()) {
                        String currentText1 = text.getText().toString().substring(0, position);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%s3√%s", currentText1, currentText2));
                        text.setSelection(position + 2);
                    } else {
                        text.append("3√");
                    }
                }
                break;

            case R.id.button_rand:

                if (position == 0) {
                    text.setText(String.format("%f%s",
                            Math.random() * 100,
                            text.getText()));
                    text.setSelection(text.length());
                } else if (position == text.length()) {
                    String formula = text.getText().toString();
                    if (!formula.isEmpty()) {
                        ArrayList<String> components = Parse.tokenize(formula);
                        String randomNumber = "-";
                        if (components.size() != 0) {
                            randomNumber = components.get(components.size() - 1);
                            if (randomNumber.charAt(0) >= 48 && randomNumber.charAt(0) <= 57) {
                                String currentText1 = text.getText().toString()
                                        .substring(0, text.getText()
                                                .toString().length() - randomNumber.length());
                                randomNumber = String.format("%f", Math.random() * 100);
                                text.setText(String.format("%s%s", currentText1, randomNumber));
                                text.setSelection(currentText1.length() + randomNumber.length());
                            } else {
                                randomNumber = String.format("%f", Math.random() * 100);
                                text.append(randomNumber);
                                text.setSelection(position + randomNumber.length());
                            }
                        } else {
                            randomNumber = String.format("%f", Math.random() * 100);
                            text.append(randomNumber);
                            text.setSelection(position + randomNumber.length() - 1);
                        }
                    }
                }
                break;

            case R.id.button_1_div_x:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s1/%s", currentText1, currentText2));
                    text.setSelection(position + 2);
                } else {
                    text.append("1/");
                }
                break;

            case R.id.button_open_Parenthesis:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s(%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("(");
                }
                break;

            case R.id.button_close_Parenthesis:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s)%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append(")");
                }
                break;

            case R.id.button_pi:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s3.14159265%s", currentText1, currentText2));
                    text.setSelection(position + 10);
                } else {
                    text.append("3.14159265");
                }
                break;

            case R.id.button_inv:
                if (!invOn) {
                    cosButton.setText(Html.fromHtml("cos<sup><small>−1</small></sup>"));
                    sinButton.setText(Html.fromHtml("sin<sup><small>−1</small></sup>"));
                    tanButton.setText(Html.fromHtml("tan<sup><small>−1</small></sup>"));
                    logButton.setText(Html.fromHtml("10<sup>x</sup>"));
                    lnButton.setText(Html.fromHtml("e<sup>x</sup>"));
                    invOn = true;
                } else {
                    cosButton.setText("cos");
                    sinButton.setText("sin");
                    tanButton.setText("tan");
                    logButton.setText("log");
                    lnButton.setText("ln");
                    invOn = false;
                }
                break;

            case R.id.button_abs:
                String formula = text.getText().toString();
                if (!formula.isEmpty()) {
                    ArrayList<String> components = Parse.tokenize(formula);
                    String lastNumberInFormula = "−";
                    if (components.size() != 0)
                        lastNumberInFormula = components.get(components.size() - 1);
                    if (lastNumberInFormula.charAt(0) >= 48 && lastNumberInFormula.charAt(0) <= 57) {
                        text.setText(String.format("%s|%s|", text.getText().
                                        toString().
                                        substring(0, text.getText().toString().length() - lastNumberInFormula.length()),
                                lastNumberInFormula));
                        text.setSelection(position + 2);
                    }
                }
                break;

            case R.id.button_cos:
                if (invOn) {
                    position = text.getSelectionStart();
                    if (position != text.length()) {
                        String currentText1 = text.getText().toString().substring(0, position);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%scos()^(−1)%s", currentText1, currentText2));
                        text.setSelection(text.toString().indexOf("cos()") + 5);
                    } else {
                        text.append("cos()^(−1)");
                        text.setSelection(text.toString().indexOf("cos()") + 5);
                    }
                } else {
                    position = text.getSelectionStart();
                    if (position != text.length()) {
                        String currentText1 = text.getText().toString().substring(0, position);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%scos(%s", currentText1, currentText2));
                        text.setSelection(position + 4);
                    } else {
                        text.append("cos(");
                    }
                }
                break;

            case R.id.button_sin:
                if (invOn) {
                    position = text.getSelectionStart();
                    if (position != text.length()) {
                        String currentText1 = text.getText().toString().substring(0, position);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%ssin()^(−1)%s", currentText1, currentText2));
                        text.setSelection(text.toString().indexOf("sin()") + 5);
                    } else {
                        text.append("sin()^(−1)");
                        text.setSelection(text.toString().indexOf("sin()") + 5);
                    }
                } else {
                    position = text.getSelectionStart();
                    if (position != text.length()) {
                        String currentText1 = text.getText().toString().substring(0, position);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%ssin(%s", currentText1, currentText2));
                        text.setSelection(position + 4);
                    } else {
                        text.append("sin(");
                    }
                }
                break;

            case R.id.button_tan:
                if (invOn) {
                    position = text.getSelectionStart();
                    if (position != text.length()) {
                        String currentText1 = text.getText().toString().substring(0, position);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%stan()^(−1)%s", currentText1, currentText2));
                        text.setSelection(text.toString().indexOf("tan()") + 5);
                    } else {
                        text.append("tan()^(−1)");
                        text.setSelection(text.toString().indexOf("tan()") + 5);
                    }
                } else {
                    position = text.getSelectionStart();
                    if (position != text.length()) {
                        String currentText1 = text.getText().toString().substring(0, position);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%stan(%s", currentText1, currentText2));
                        text.setSelection(position + 4);
                    } else {
                        text.append("tan(");
                    }
                }
                break;

            case R.id.button_log:
                if (invOn) {
                    position = text.getSelectionStart();
                    if (position != text.length()) {
                        String currentText1 = text.getText().toString().substring(0, position);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%s10^(%s", currentText1, currentText2));
                        text.setSelection(position + 4);
                    } else {
                        text.append("10^(");
                    }
                } else {
                    position = text.getSelectionStart();
                    if (position != text.length()) {
                        String currentText1 = text.getText().toString().substring(0, position);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%slog(%s", currentText1, currentText2));
                        text.setSelection(position + 4);
                    } else {
                        text.append("log(");
                    }
                }
                break;

            case R.id.button_ln:
                if (invOn) {
                    position = text.getSelectionStart();
                    if (position != text.length()) {
                        String currentText1 = text.getText().toString().substring(0, position);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%se^(%s", currentText1, currentText2));
                        text.setSelection(position + 3);
                    } else {
                        text.append("e^(");
                    }
                } else {
                    position = text.getSelectionStart();
                    if (position != text.length()) {
                        String currentText1 = text.getText().toString().substring(0, position);
                        String currentText2 = text.getText().toString().substring(position);
                        text.setText(String.format("%sln(%s", currentText1, currentText2));
                        text.setSelection(position + 3);
                    } else {
                        text.append("ln(");
                    }
                }
                break;

            case R.id.button_e:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%se^(%s", currentText1, currentText2));
                    text.setSelection(position + 3);
                } else {
                    text.append("e^(");
                }
                break;
            case R.id.button_x_fact:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s!%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append("!");
                }
                break;

            case R.id.button_exp:
                position = text.getSelectionStart();
                if (position != text.length()) {
                    String currentText1 = text.getText().toString().substring(0, position);
                    String currentText2 = text.getText().toString().substring(position);
                    text.setText(String.format("%s.e+0%s", currentText1, currentText2));
                    text.setSelection(position + 1);
                } else {
                    text.append(".e+0");
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
                        text.setSelection(position - 1);
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

