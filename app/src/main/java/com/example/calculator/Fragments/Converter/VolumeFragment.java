package com.example.calculator.Fragments.Converter;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
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

public class VolumeFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private View view;
    private Spinner sourceVolume;
    private Spinner targetVolume;
    private TextView sourceVolumeAmount;
    private TextView targetVolumeAmount;
    private TextView volumeInfo;
    private ImageView imageView;
    private Button c;
    private Button del;
    private Button dot;
    private Button[] button;
    private final double LITER_EQUAL_GALLON_US = 0.2641720524;
    private final double LITER_EQUAL_GALLON_UK = 0.2199692483;
    private final double GALLON_US_EQUAL_LITER = 3.785411784;
    private final double GALLON_UK_EQUAL_LITER = 4.54609;
    private final double GALLON_US_EQUAL_GALLON_UK = 0.8326741846;
    private final double GALLON_UK_EQUAL_GALLON_US = 1.2009499255;
    private final double GALLON_UK_EQUAL_CUPS = 19.22;
    private final double GALLON_US_EQUAL_CUPS = 16;
    private final double LETTER_EQUAL_CUPS = 4.227;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_volume, container, false);
        initializeVariables();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.volumes, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(view.getContext(), R.array.volumes, android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceVolume.setAdapter(adapter);
        targetVolume.setAdapter(adapter2);
        sourceValueListener();
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view1, int i, long l) {

        String selectedItem = adapterView.getItemAtPosition(i).toString();
        switch (adapterView.getId()) {
            case R.id.sourceVolume:
            case R.id.targetVolume:
                convertVolumes();
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
        sourceVolume = view.findViewById(R.id.sourceVolume);
        targetVolume = view.findViewById(R.id.targetVolume);
        sourceVolumeAmount = view.findViewById(R.id.sourceVolumeAmount);
        targetVolumeAmount = view.findViewById(R.id.targetVolumeAmount);
        volumeInfo = view.findViewById(R.id.volume_info);
        imageView = view.findViewById(R.id.imageView);
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
        sourceVolume.setOnItemSelectedListener(this);
        targetVolume.setOnItemSelectedListener(this);

    }


    @Override
    public void onClick(View view) {

        if (sourceVolumeAmount.getText().toString().length() == 1 && sourceVolumeAmount.getText().toString().equals("0")) {
            sourceVolumeAmount.setText("");
        }
        switch (view.getId()) {
            case R.id.button_0:
                sourceVolumeAmount.append("0");
                break;

            case R.id.button_1:
                sourceVolumeAmount.append("1");
                break;

            case R.id.button_2:
                sourceVolumeAmount.append("2");
                break;

            case R.id.button_3:
                sourceVolumeAmount.append("3");
                break;

            case R.id.button_4:
                sourceVolumeAmount.append("4");
                break;

            case R.id.button_5:
                sourceVolumeAmount.append("5");
                break;

            case R.id.button_6:
                sourceVolumeAmount.append("6");
                break;

            case R.id.button_7:
                sourceVolumeAmount.append("7");
                break;

            case R.id.button_8:
                sourceVolumeAmount.append("8");
                break;

            case R.id.button_9:
                sourceVolumeAmount.append("9");
                break;

            case R.id.button_dot:
                sourceVolumeAmount.append(".");
                break;

            case R.id.button_clear: {
                sourceVolumeAmount.setText("0");
                targetVolumeAmount.setText("0");
                volumeInfo.setText("");
                imageView.setImageResource(0);
            }
            break;

            case R.id.button_del: {
                String temp = sourceVolumeAmount.getText().toString();
                if (!temp.isEmpty())
                    sourceVolumeAmount.setText(temp.substring(0, temp.length() - 1));
                if (temp.length() == 0) {
                    sourceVolumeAmount.setText("0");
                    targetVolumeAmount.setText("0");
                    volumeInfo.setText("");
                    imageView.setImageResource(0);
                }
            }
            break;
        }
    }

    void sourceValueListener() {

        sourceVolumeAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (sourceVolumeAmount.getText().toString().isEmpty()) {
                    targetVolumeAmount.setText("0");
                    volumeInfo.setText("");
                    imageView.setImageResource(0);
                }
                convertVolumes();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    void convertVolumes() {
        try {
            if (!sourceVolumeAmount.getText().toString().isEmpty()) {
                if (sourceVolume != null && sourceVolume.getSelectedItem().toString().equals("Liters ▼")) {
                    double value = Double.parseDouble(sourceVolumeAmount.getText().toString());
                    if (targetVolume != null && targetVolume.getSelectedItem().toString().equals("Gallons (US) ▼")) {
                        targetVolumeAmount.setText(value * LITER_EQUAL_GALLON_US + "");
                    } else if (targetVolume != null && targetVolume.getSelectedItem().toString().equals("Gallons (UK) ▼")) {
                        targetVolumeAmount.setText(value * LITER_EQUAL_GALLON_UK + "");
                    } else {
                        targetVolumeAmount.setText(sourceVolumeAmount.getText().toString());
                    }
                    if (value != 0) {
                        volumeInfo.setText((int)(value * LETTER_EQUAL_CUPS) + " Coffee Cups");
                        imageView.setImageResource(R.drawable.ic_baseline_coffee_white_24);
                    }
                }
                if (sourceVolume != null && sourceVolume.getSelectedItem().toString().equals("Gallons (US) ▼")) {
                    double value = Double.parseDouble(sourceVolumeAmount.getText().toString());
                    if (targetVolume != null && targetVolume.getSelectedItem().toString().equals("Liters ▼")) {
                        targetVolumeAmount.setText(value * GALLON_US_EQUAL_LITER + "");
                    } else if (targetVolume != null && targetVolume.getSelectedItem().toString().equals("Gallons (UK) ▼")) {
                        targetVolumeAmount.setText(value * GALLON_US_EQUAL_GALLON_UK + "");
                    } else {
                        targetVolumeAmount.setText(sourceVolumeAmount.getText().toString());
                    }
                    if (value != 0) {
                        volumeInfo.setText((int)(value * GALLON_US_EQUAL_CUPS) + " Coffee Cups");
                        imageView.setImageResource(R.drawable.ic_baseline_coffee_white_24);
                    }
                }
                if (sourceVolume != null && sourceVolume.getSelectedItem().toString().equals("Gallons (UK) ▼")) {
                    double value = Double.parseDouble(sourceVolumeAmount.getText().toString());
                    if (targetVolume != null && targetVolume.getSelectedItem().toString().equals("Liters ▼")) {
                        targetVolumeAmount.setText(value * GALLON_UK_EQUAL_LITER + "");
                    } else if (targetVolume != null && targetVolume.getSelectedItem().toString().equals("Gallons (US) ▼")) {
                        targetVolumeAmount.setText(value * GALLON_UK_EQUAL_GALLON_US + "");
                    } else {
                        targetVolumeAmount.setText(sourceVolumeAmount.getText().toString());
                    }
                    if (value != 0) {
                        volumeInfo.setText((int)(value * GALLON_UK_EQUAL_CUPS) + " Coffee Cups");
                        imageView.setImageResource(R.drawable.ic_baseline_coffee_white_24);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
