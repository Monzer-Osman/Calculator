package com.example.calculator.Fragments.Converter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.calculator.R;

public class Length_Fragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    
    View view;
    private Spinner sourceLength;
    private Spinner targetLength;
    private TextView sourceLengthAmount;
    private TextView targetLengthAmount;
    private TextView lengthInfo;
    private Button c;
    private Button del;
    private Button dot;
    private Button[] button;
    private final double CENTIMETERS_EQUAL_METERS = 0.01;
    private final double CENTIMETERS_EQUAL_KILOMETERS = 0.00001;
    private final double CENTIMETERS_EQUAL_MILES = 0.000006;
    private final double METERS_EQUAL_CENTIMETERS = 100;
    private final double METERS_EQUAL_KILOMETERS = 0.001;
    private final double METERS_EQUAL_MILES = 0.000621;
    private final double KILOMETERS_EQUAL_CENTIMETERS = 100000;
    private final double KILOMETERS_EQUAL_METERS = 1000;
    private final double KILOMETERS_EQUAL_MILES = 0.621371;
    private final double MILES_EQUAL_CENTIMETERS = 160934.4;
    private final double MILES_EQUAL_METERS = 1609.344;
    private final double MILES_EQUAL_KILOMETERS = 1.609344;
    private final double CENTIMETERS_EQUAL_NMI = 0.000005;
    private final double METERS_EQUAL_NMI = 0.00054;
    private final double KILOMETERS_EQUAL_NMI = 0.539957;
    private final double MILES_EQUAL_NMI = 0.868976;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_length, container, false);

        initializeVariables();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.lengths, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(view.getContext(), R.array.lengths, android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceLength.setAdapter(adapter);
        targetLength.setAdapter(adapter2);
        sourceValueListener();

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view1, int i, long l) {

        String selectedItem = adapterView.getItemAtPosition(i).toString();
        switch (adapterView.getId()) {
            case R.id.sourceLength:
            case R.id.targetLength:
                convertLengths();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
        c = view.findViewById(R.id.button_clear);
        del = view.findViewById(R.id.button_del);
        dot = view.findViewById(R.id.button_dot);
        sourceLength = view.findViewById(R.id.sourceLength);
        targetLength = view.findViewById(R.id.targetLength);
        sourceLengthAmount = view.findViewById(R.id.sourceLengthAmount);
        targetLengthAmount = view.findViewById(R.id.targetLengthAmount);
        lengthInfo = view.findViewById(R.id.length_info);
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
        dot.setOnClickListener(this);
        sourceLength.setOnItemSelectedListener(this);
        targetLength.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {

        if (sourceLengthAmount.getText().toString().length() == 1 && sourceLengthAmount.getText().toString().equals("0")) {
            sourceLengthAmount.setText("");
        }
        switch (view.getId()) {
            case R.id.button_0:
                sourceLengthAmount.append("0");
                break;

            case R.id.button_1:
                sourceLengthAmount.append("1");
                break;

            case R.id.button_2:
                sourceLengthAmount.append("2");
                break;

            case R.id.button_3:
                sourceLengthAmount.append("3");
                break;

            case R.id.button_4:
                sourceLengthAmount.append("4");
                break;

            case R.id.button_5:
                sourceLengthAmount.append("5");
                break;

            case R.id.button_6:
                sourceLengthAmount.append("6");
                break;

            case R.id.button_7:
                sourceLengthAmount.append("7");
                break;

            case R.id.button_8:
                sourceLengthAmount.append("8");
                break;

            case R.id.button_9:
                sourceLengthAmount.append("9");
                break;

            case R.id.button_dot:
                sourceLengthAmount.append(".");
                break;

            case R.id.button_clear: {
                sourceLengthAmount.setText("0");
                targetLengthAmount.setText("0");
                lengthInfo.setText("");
            }
            break;

            case R.id.button_del: {
                String temp = sourceLengthAmount.getText().toString();
                if (!temp.isEmpty())
                    sourceLengthAmount.setText(temp.substring(0, temp.length() - 1));
                if (temp.length() == 0) {
                    sourceLengthAmount.setText("0");
                    targetLengthAmount.setText("0");
                    lengthInfo.setText("");
                }
            }
            break;
        }
    }

    void sourceValueListener() {

        sourceLengthAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (sourceLengthAmount.getText().toString().isEmpty()) {
                    targetLengthAmount.setText("0");
                    lengthInfo.setText("");
                }
                convertLengths();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    void convertLengths() {
        try {
            if (!sourceLengthAmount.getText().toString().isEmpty()) {
                if (sourceLength != null && sourceLength.getSelectedItem().toString().equals("Centimeters ▼")) {
                    double value = Double.parseDouble(sourceLengthAmount.getText().toString());
                    if (targetLength != null && targetLength.getSelectedItem().toString().equals("Meters ▼")) {
                        targetLengthAmount.setText(value * CENTIMETERS_EQUAL_METERS + "");
                    } else if (targetLength != null && targetLength.getSelectedItem().toString().equals("Kilometers ▼")) {
                        targetLengthAmount.setText(value * CENTIMETERS_EQUAL_KILOMETERS + "");
                    }
                    else if (targetLength != null && targetLength.getSelectedItem().toString().equals("Miles ▼")) {
                        targetLengthAmount.setText(value * CENTIMETERS_EQUAL_MILES + "");
                    } else {
                        targetLengthAmount.setText(sourceLengthAmount.getText().toString());
                    }
                    if (value != 0) {
                        lengthInfo.setText(value * CENTIMETERS_EQUAL_NMI + " Nautical Miles");
                    }
                }
                if (sourceLength != null && sourceLength.getSelectedItem().toString().equals("Meters ▼")) {
                    double value = Double.parseDouble(sourceLengthAmount.getText().toString());
                    if (targetLength != null && targetLength.getSelectedItem().toString().equals("Centimeters ▼")) {
                        targetLengthAmount.setText(value * METERS_EQUAL_CENTIMETERS + "");
                    } else if (targetLength != null && targetLength.getSelectedItem().toString().equals("Kilometers ▼")) {
                        targetLengthAmount.setText(value * METERS_EQUAL_KILOMETERS + "");
                    }else if (targetLength != null && targetLength.getSelectedItem().toString().equals("Miles ▼")) {
                        targetLengthAmount.setText(value * METERS_EQUAL_MILES + "");
                    } else {
                        targetLengthAmount.setText(sourceLengthAmount.getText().toString());
                    }
                    if (value != 0) {
                        lengthInfo.setText(value * METERS_EQUAL_NMI + " Nautical Miles");
                    }
                }
                if (sourceLength != null && sourceLength.getSelectedItem().toString().equals("Kilometers ▼")) {
                    double value = Double.parseDouble(sourceLengthAmount.getText().toString());
                    if (targetLength != null && targetLength.getSelectedItem().toString().equals("Centimeters ▼")) {
                        targetLengthAmount.setText(value * KILOMETERS_EQUAL_CENTIMETERS + "");
                    } else if (targetLength != null && targetLength.getSelectedItem().toString().equals("Meters ▼")) {
                        targetLengthAmount.setText(value * KILOMETERS_EQUAL_METERS + "");
                    } else if (targetLength != null && targetLength.getSelectedItem().toString().equals("Miles ▼")) {
                        targetLengthAmount.setText(value * KILOMETERS_EQUAL_MILES + "");
                    } else {
                        targetLengthAmount.setText(sourceLengthAmount.getText().toString());
                    }
                    if (value != 0) {
                        lengthInfo.setText(value * KILOMETERS_EQUAL_NMI + " Nautical Miles");
                    }
                }
                if (sourceLength != null && sourceLength.getSelectedItem().toString().equals("Miles ▼")) {
                    double value = Double.parseDouble(sourceLengthAmount.getText().toString());
                    if (targetLength != null && targetLength.getSelectedItem().toString().equals("Centimeters ▼")) {
                        targetLengthAmount.setText(value * MILES_EQUAL_CENTIMETERS + "");
                    } else if (targetLength != null && targetLength.getSelectedItem().toString().equals("Meters ▼")) {
                        targetLengthAmount.setText(value * MILES_EQUAL_METERS + "");
                    } else if (targetLength != null && targetLength.getSelectedItem().toString().equals("Kilometers ▼")) {
                        targetLengthAmount.setText(value * MILES_EQUAL_KILOMETERS + "");
                    } else {
                        targetLengthAmount.setText(sourceLengthAmount.getText().toString());
                    }
                    if (value != 0) {
                        lengthInfo.setText(value * MILES_EQUAL_NMI + " Nautical Miles");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}