package com.example.exchangerates.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.R
import com.example.exchangerates.RecyclerAdapter
import com.example.exchangerates.SimpleAPIRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

const val BASE_URL = "http://data.fixer.io/api/"

class WeekRatesFragment : Fragment() {

    private var datesList = mutableListOf<String>()
    private var valueList = mutableListOf<String>()
    private var baseRate: String = "EUR"

    private lateinit var weekRatesRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        makeAPIRequest()
        return inflater.inflate(R.layout.fragment_week_rates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weekRatesRecycler = view.findViewById(R.id.weekRatesRecycler)

        weekRatesRecycler.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = RecyclerAdapter(datesList, baseRate, valueList)
        }

    }

    private fun makeAPIRequest(){
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SimpleAPIRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getRates()
                baseRate = response.base
                addToList(response.date, "AUD " + response.myRates.aUD.toString(), "CAD "+response.myRates.cAD.toString())
                // check random values from response

//                Log.i("Main", "Result of response = ${response.myRates.aED} + ${response.myRates.aFN} + ${response.myRates.iDR} + ${response.myRates.jOD}")
//                Log.i("Main", "Result of response = ${response.date}")
//                Log.i("Main", "Result of response = ${response.base}")
//                Log.i("Main", "Result of response = ${response.success}")
            }catch (e: Exception){
                Log.e("WeekRatesFragment", "Error ${e.message}")
            }
        }
    }

    private fun addToList(date: String, value: String, value2: String){
        datesList.add(date)
        valueList.add(value)
        valueList.add(value2)
    }
}