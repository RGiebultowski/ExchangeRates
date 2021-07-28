package com.example.exchangerates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.fragment.app.Fragment
import com.example.exchangerates.fragments.CustomDateFragment
import com.example.exchangerates.fragments.LastRatesFragment
import com.example.exchangerates.fragments.WeekRatesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val weekRatesFragment = WeekRatesFragment()
    private val lastRatesFragment = LastRatesFragment()
    private val customDateFragment = CustomDateFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)


        // launch app with last rates fragment
        changeFragments(lastRatesFragment)


        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.lastRates -> changeFragments(lastRatesFragment)
                R.id.weekRates -> changeFragments(weekRatesFragment)
                R.id.customDate -> changeFragments(customDateFragment)
            }
            true
        }

    }

    private fun changeFragments(fragment: Fragment){
        if (fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
    }


}