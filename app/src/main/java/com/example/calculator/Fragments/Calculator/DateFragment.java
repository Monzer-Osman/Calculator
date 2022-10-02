package com.example.calculator.Fragments;

import static java.lang.String.*;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.calculator.R;

import java.util.Calendar;

public class DateFragment extends Fragment implements View.OnClickListener {
    View view;
    DatePickerDialog datePickerDialog;
    DatePickerDialog datePickerDialog2;

    Button dateButton;
    Button dateButton2;
    TextView differenceValue;
    TextView totalNumberOfDays;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_date, container, false);
        initDatePicker();
        differenceValue = view.findViewById(R.id.date_differenceValue);
        totalNumberOfDays = view.findViewById(R.id.totalNumberOfDays);
        dateButton = view.findViewById(R.id.datePickerButton1);
        dateButton2 = view.findViewById(R.id.datePickerButton2);
        dateButton.setOnClickListener(this);
        dateButton2.setOnClickListener(this);
        dateButton.setText(getTodayDate());
        dateButton2.setText(getTodayDate());
        Log.d("date1.1", dateButton.getText().toString());
        Log.d("date2.1", dateButton2.getText().toString());
        differenceValue.setText("Same Dates");
        return view;
    }

    private String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(year, month + 1, day);
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = makeDateString(year, month + 1, day);
                dateButton.setText(date);

                int[] differences = calculateDateDifference();
                differenceValue.setText(getTotalDifference(differences[0], differences[1], differences[2]));
                if (getTotalDays(differences[0], differences[1], differences[2]) != 0) {
                    totalNumberOfDays.setText(format("%d days", getTotalDays(differences[0], differences[1], differences[2])));
                }
            }
        };

        DatePickerDialog.OnDateSetListener dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = makeDateString(year, month + 1, day);
                dateButton2.setText(date);

                int[] differences = calculateDateDifference();
                differenceValue.setText(getTotalDifference(differences[0], differences[1], differences[2]));
                if (getTotalDays(differences[0], differences[1], differences[2]) != 0) {
                    totalNumberOfDays.setText(format("%d days", getTotalDays(differences[0], differences[1], differences[2])));
                }
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(view.getContext(), style, dateSetListener, year, month, day);
        datePickerDialog2 = new DatePickerDialog(view.getContext(), style, dateSetListener2, year, month, day);
    }

    private String getTotalDifference(int differenceOfYears, int differenceOfMonths, int differenceOfDays) {

        String totalDifference = "";
        if (differenceOfYears >= 1) {
            if (differenceOfDays >= 1 || differenceOfMonths >= 1)
                totalDifference += differenceOfYears + " year, ";
            else
                totalDifference += differenceOfYears + " year";
        }
        if (differenceOfMonths >= 1) {
            if (differenceOfDays >= 1)
                totalDifference += differenceOfMonths + " month, ";
            else
                totalDifference += differenceOfMonths + " month";
        }
        if (differenceOfDays / 7 >= 1) {
            if (differenceOfDays % 7 >= 1)
                totalDifference += (differenceOfDays / 7) + " weeks, ";
            else
                totalDifference += (differenceOfDays / 7) + " weeks";
        }
        if (differenceOfDays % 7 >= 1) {
            totalDifference += (differenceOfDays % 7) + " days";
        }
        if (totalDifference.isEmpty()) {
            return "Same Dates";
        } else {
            return totalDifference;
        }
    }

    private int getTotalDays(int differenceOfYears, int differenceOfMonths, int differenceOfDays) {

        int totalMonthsDays = 0;
        int month = getMonthNumber();

        for(int i = month; i < month + differenceOfMonths; i++){
            totalMonthsDays += getMonthDaysCount(month);
        }

        return differenceOfDays + totalMonthsDays + (differenceOfYears * 365);
    }

    private int getMonthNumber(){
        String date1 = dateButton.getText().toString();
        String date2 = dateButton2.getText().toString();
        int month1 = getMonthNumber(date1.split(" ")[0]);
        int month2 = getMonthNumber(date2.split(" ")[0]);

        if(month1 < month2){
            return month1;
        }
        else{
            return month2;
        }
    }

    private int[] calculateDateDifference() {

        String date1 = dateButton.getText().toString();
        String date2 = dateButton2.getText().toString();

        if (!date1.isEmpty() && !date2.isEmpty()) {
            String day1 = date1.split(", ")[0].split(" ")[1].replace(',', ' ').trim();
            String day2 = date2.split(", ")[0].split(" ")[1].replace(',', ' ').trim();
            String month1 = date1.split(" ")[0];
            String month2 = date2.split(" ")[0];
            String year1 = date1.split(", ")[1];
            String year2 = date2.split(", ")[1];

            int differenceOfYears = Math.abs(Integer.parseInt(year1) - Integer.parseInt(year2));
            int differenceOfMonths = Math.abs(getMonthNumber(month1) - getMonthNumber(month2));
            int differenceOfDays = Math.abs(Integer.parseInt(day1) - Integer.parseInt(day2));

            return new int[]{differenceOfYears, differenceOfMonths, differenceOfDays};
        }

        return new int[]{0, 0, 0};
    }

    private String makeDateString(int year, int month, int day) {

        return getMonthFormat(month) + " " + day + ", " + year;
    }

    private String getMonthFormat(int i1) {

        switch (i1) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
        }
        return "January";
    }

    private int getMonthNumber(String month) {

        switch (month) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
        }
        return 0;
    }

    private int getMonthDaysCount(int i1) {

        switch (i1) {
            case 2:
                return 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
        }
        return 30;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.datePickerButton1) {
            datePickerDialog.show();
        }

        if (view.getId() == R.id.datePickerButton2) {
            datePickerDialog2.show();
        }
    }
}