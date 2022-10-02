package com.example.calculator.Interfaces;

import com.example.calculator.Model.Currency;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("USD")
    Call<Currency> getCurrencyRatesUSD();

    @GET("SAR")
    Call<Currency> getCurrencyRatesSAR();
}
