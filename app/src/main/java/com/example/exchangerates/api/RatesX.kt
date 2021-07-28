package com.example.exchangerates.api


import com.google.gson.annotations.SerializedName

data class RatesX(
    @SerializedName("AUD")
    val aUD: Double,
    @SerializedName("CAD")
    val cAD: Double,
    @SerializedName("MXN")
    val mXN: Double,
    @SerializedName("PLN")
    val pLN: Double,
    @SerializedName("USD")
    val uSD: Double
)