package com.example.exchangerates.api


import com.google.gson.annotations.SerializedName

data class HistoricalRates(
    val base: String,
    val date: String,
    val historical: Boolean,
    @SerializedName("rates")
    val historicalRates: RatesX,
    val success: Boolean,
    val timestamp: Int
)