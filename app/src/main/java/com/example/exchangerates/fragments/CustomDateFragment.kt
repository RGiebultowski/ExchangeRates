package com.example.exchangerates.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exchangerates.R
import com.example.exchangerates.SimpleAPIRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class CustomDateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        makeHistoricalApiRequest()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_date, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun makeHistoricalApiRequest(){
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SimpleAPIRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getHistoricalRates("2013-03-16")
                // check random values from response
                Log.i("Main", "Result of response = ${response.historicalRates.pLN} + ${response.historicalRates.uSD}")
                Log.i("Main", "Result of response = ${response.date}")
                Log.i("Main", "Result of response = ${response.base}")
                Log.i("Main", "Result of response = ${response.success}")
                Log.i("Main", "Result of response = ${response.historical}")
            }catch (e: Exception){
                Log.e("Historical", "Error ${e.message}")
            }
        }
    }
}
