package com.example.exchangerates.splash

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.exchangerates.MainActivity
import com.example.exchangerates.R
import com.example.exchangerates.SimpleAPIRequest
import com.example.exchangerates.fragments.LastRatesFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

const val BASE_URL = "http://data.fixer.io/api/"

class SplashScreenActivity : AppCompatActivity() {

//    private val SPLASH_TIME:Long = 5000 // ms
    lateinit var context: Context
    var todayDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        context = this
        getAPIRequest().execute()

    }

    internal inner class getAPIRequest: AsyncTask<Void, Void, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Void?): String {
            if (p0 != null){
                val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SimpleAPIRequest::class.java)

                GlobalScope.launch(Dispatchers.IO) {
                    try {
                        val response = api.getRates()
                        todayDate = response.date

                    }catch (e: Exception){
                        Log.e("SplashScreen", "Error ${e.message}")
                    }
                }
                return todayDate
            }
            return ""
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            Log.i("PostExecute", result)
            var lastFragment = LastRatesFragment()
            lastFragment.addToList(result)
            startActivity(Intent(context, MainActivity::class.java))
            finish()
        }

    }
}