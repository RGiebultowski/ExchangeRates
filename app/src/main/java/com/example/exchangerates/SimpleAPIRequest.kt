package com.example.exchangerates

import com.example.exchangerates.api.HistoricalRates
import com.example.exchangerates.api.RatesAPIJson
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "b56e1f5c45fec316362755b4d002b670"

interface SimpleAPIRequest {

    @GET("latest")
    suspend fun getRates(@Query("access_key") access_key: String = API_KEY) : RatesAPIJson

    @GET("latest")
    suspend fun getHistoricalRates(@Query("") date: String = "",
                                   @Query("access_key") access_key: String = API_KEY) : HistoricalRates
}