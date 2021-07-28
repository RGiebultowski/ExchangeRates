package com.example.exchangerates.api

import com.google.gson.annotations.SerializedName

data class RatesAPIJson(
    val base: String,
    val date: String,
    @SerializedName("rates")
    val myRates: Rates,
    val success: Boolean,
    val timestamp: Int
)