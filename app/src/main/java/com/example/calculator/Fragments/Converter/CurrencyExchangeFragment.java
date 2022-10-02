package com.example.calculator.Fragments.Converter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator.Interfaces.ApiInterface;
import com.example.calculator.Model.Currency;
import com.example.calculator.Model.Response;
import com.example.calculator.Network.ApiClient;
import com.example.calculator.R;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;

public class CurrencyExchangeFragment extends Fragment implements View.OnClickListener {

    private static final String SHARED_PREFS = "sharedPrefs";
    private View view;
    private SharedPreferences cachMemory;
    private TextView sourceCurrencyAmount;
    private TextView targetCurrencyAmount;
    private TextView statusInfo;
    private TextView status;
    private TextView currencyType;
    private TextView currencyType2;
    private Currency exchangeRates;
    private CountryCurrencyButton countryName;
    private CountryCurrencyButton countryName2;
    private Button c;
    private Button del;
    private Button dot;
    private Button[] button;
    private String countryCode1 = "";
    private String countryCode2 = "";
    private Date lastUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_currency_exchange, container, false);
        initializeVariables();

        refreshData("USD");
        try {
            String[] temp = readFromFile(countryName.getCountry().getName().toString());
            if (temp.length >= 2) {
                countryCode1 = temp[temp.length - 2];
                currencyType.setText(temp[temp.length - 1]);
            }
            temp = readFromFile(countryName2.getCountry().getName().toString());

            if (temp.length >= 2) {
                countryCode2 = temp[temp.length - 2];
                currencyType2.setText(temp[temp.length - 1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        sourceCurrencyAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (sourceCurrencyAmount.getText().toString().isEmpty()) {
                    targetCurrencyAmount.setText("0");
                }
                if (countryName != null) {
                    if (exchangeRates != null && !sourceCurrencyAmount.getText().toString().isEmpty()) {
                        try {
                            float currency = Float.parseFloat(sourceCurrencyAmount.getText().toString());
                            float country1 = exchangeRates.conversion_rates.get(countryCode1);
                            float country2 = exchangeRates.conversion_rates.get(countryCode2);
                            targetCurrencyAmount.setText((country2 / country1) * currency + "");
                        }catch (Exception e){
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        countryName.setOnClickListener(new CountryCurrencyPickerListener() {
            @Override
            public void onSelectCountry(Country country) {
                if (country != null && exchangeRates != null) {
                    try {
                        String[] countryInfo = readFromFile(country.getName().toString());
                        if (countryInfo.length >= 2 && exchangeRates.conversion_rates != null) {
                            countryCode1 = countryInfo[countryInfo.length - 2];
                            currencyType.setText(countryInfo[countryInfo.length - 1]);
                            if(!exchangeRates.getBase_code().equals("SAR") && countryCode1.equals("SAR"))
                                refreshData("SAR");
                            else if(!exchangeRates.getBase_code().equals("USD")){
                                refreshData("USD");
                            }
                            float temp1 = exchangeRates.conversion_rates.get(countryCode1);
                            float temp2 = exchangeRates.conversion_rates.get(countryCode2) / temp1;
                            statusInfo.setText(1 + " " + currencyType.getText().toString() + " = " + temp2 + " " + currencyType2.getText().toString() + "\nUpdated " + lastUpdate.toString());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onSelectCurrency(com.scrounger.countrycurrencypicker.library.Currency currency) {

            }
        });

        countryName2.setOnClickListener(new CountryCurrencyPickerListener() {
            @Override
            public void onSelectCountry(Country country) {
                if (country != null && exchangeRates != null) {
                    try {
                        String[] countryInfo = readFromFile(country.getName().toString());
                        if (countryInfo.length >= 2 && exchangeRates.conversion_rates != null) {
                            currencyType2.setText(countryInfo[countryInfo.length - 1]);
                            countryCode2 = countryInfo[countryInfo.length - 2];
                            float temp1 = exchangeRates.conversion_rates.get(countryCode1);
                            float temp2 = exchangeRates.conversion_rates.get(countryCode2) / temp1;
                            statusInfo.setText(1 + " " + currencyType.getText().toString() + " = " + temp2 + " " + currencyType2.getText().toString() + "\nUpdated " + lastUpdate.toString());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onSelectCurrency(com.scrounger.countrycurrencypicker.library.Currency currency) {

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
        c = view.findViewById(R.id.button_clear);
        del = view.findViewById(R.id.button_del);
        dot = view.findViewById(R.id.button_dot);
        statusInfo = view.findViewById(R.id.currency_info);
        status = view.findViewById(R.id.status);
        sourceCurrencyAmount = view.findViewById(R.id.sourceCurrencyAmount);
        targetCurrencyAmount = view.findViewById(R.id.targetCurrencyAmount);
        countryName = view.findViewById(R.id.countryName1);
        countryName2 = view.findViewById(R.id.countryName2);
        currencyType = view.findViewById(R.id.currency_type_1);
        currencyType2 = view.findViewById(R.id.currency_type_2);
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
        sourceCurrencyAmount.setOnClickListener(this);
        targetCurrencyAmount.setOnClickListener(this);
        countryName.setOnClickListener(this);
        countryName2.setOnClickListener(this);
        countryName.setShowCurrency(false);
        countryName2.setShowCurrency(false);
    }

    @Override
    public void onClick(View view) {

        if (sourceCurrencyAmount.getText().toString().length() == 1 && sourceCurrencyAmount.getText().toString().equals("0")) {
            sourceCurrencyAmount.setText("");
        }
        switch (view.getId()) {
            case R.id.button_0:
                sourceCurrencyAmount.append("0");
                break;

            case R.id.button_1:
                sourceCurrencyAmount.append("1");
                break;

            case R.id.button_2:
                sourceCurrencyAmount.append("2");
                break;

            case R.id.button_3:
                sourceCurrencyAmount.append("3");
                break;

            case R.id.button_4:
                sourceCurrencyAmount.append("4");
                break;

            case R.id.button_5:
                sourceCurrencyAmount.append("5");
                break;

            case R.id.button_6:
                sourceCurrencyAmount.append("6");
                break;

            case R.id.button_7:
                sourceCurrencyAmount.append("7");
                break;

            case R.id.button_8:
                sourceCurrencyAmount.append("8");
                break;

            case R.id.button_9:
                sourceCurrencyAmount.append("9");
                break;

            case R.id.button_dot:
                sourceCurrencyAmount.append(".");
                break;


            case R.id.button_clear: {
                sourceCurrencyAmount.setText("0");
                targetCurrencyAmount.setText("0");
            }
            break;

            case R.id.button_del: {
                String temp = sourceCurrencyAmount.getText().toString();
                if (!temp.isEmpty())
                    sourceCurrencyAmount.setText(temp.substring(0, temp.length() - 1));
                if (temp.length() == 0) {
                    sourceCurrencyAmount.setText("0");
                    targetCurrencyAmount.setText("0");
                }
            }
            break;

        }
    }

    public void refreshData(String countryCode) {

        if (countryCode.equals("USD")) {
            ApiClient.getClient().create(ApiInterface.class).
                    getCurrencyRatesUSD().enqueue(new Callback<Currency>() {
                        @Override
                        public void onResponse(Call<Currency> call, retrofit2.Response<Currency> response) {
                            if (response.body() != null) {
                                Log.d("success", response.body().getResult());
                                exchangeRates = response.body();
                                lastUpdate = new Date();
                                exchangeRates.conversion_rates.setUSD(1);
                                float temp1 = 1;
                                float temp2 = exchangeRates.conversion_rates.get(countryCode2) / temp1;
                                statusInfo.setText(1 + " " + currencyType.getText().toString() + " = " + temp2 + " " + currencyType2.getText().toString() + "\nUpdated " + lastUpdate.toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<Currency> call, Throwable t) {
                            status.setText("Offline, " + "check your network connection :(");
                        }
                    });
        } else if (countryCode.equals("SAR")) {
            ApiClient.getClient().create(ApiInterface.class).
                    getCurrencyRatesSAR().enqueue(new Callback<Currency>() {
                        @Override
                        public void onResponse(Call<Currency> call, retrofit2.Response<Currency> response) {
                            if (response.body() != null) {
                                exchangeRates = response.body();
                                lastUpdate = new Date();
                                exchangeRates.conversion_rates.setUSD(1);
                                float temp1 = 1;
                                float temp2 = exchangeRates.conversion_rates.get(countryCode2) / temp1;
                                statusInfo.setText(1 + " " + currencyType.getText().toString() + " = " + temp2 + " " + currencyType2.getText().toString() + "\nUpdated " + lastUpdate.toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<Currency> call, Throwable t) {
                            status.setText("Offline, " + "check your network connection :(");
                        }
                    });
        }
    }

//    public void saveData(Currency exchangeRates) {
//        cachMemory = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = cachMemory.edit();
//        editor.putString("currency", exchangeRates.toString());
//        editor.commit();
//    }
//
//    public void loadData() {
//        cachMemory = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
//        String data = cachMemory.getString("currency", "");
//    }

    public String[] readFromFile(String country) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("CountryCurrency.txt")));
        if (bufferedReader != null) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] countryInfo = line.split(" ");
                if (countryInfo.length == 3) {
                    if (countryInfo[0].equalsIgnoreCase(country)) {
                        return countryInfo;
                    }
                } else if (countryInfo.length > 3) {
                    String temp = "";
                    for (int i = 0; i < countryInfo.length - 2; i++) {
                        temp += countryInfo[i] + " ";
                    }
                    temp = temp.trim();
                    System.out.println(temp);
                    if (temp.equalsIgnoreCase(country)) {
                        return countryInfo;
                    }
                }
            }
        }
        return new String[]{""};
    }
}