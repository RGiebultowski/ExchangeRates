package com.example.exchangerates.fragments

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.R
import com.example.exchangerates.RecyclerAdapter


class LastRatesFragment : Fragment() {

    private var datesList = mutableListOf<String>()
    private var baseRate: String = "EUR"
    private var valueList = mutableListOf<String>()

    private lateinit var lastRatesRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_last_rates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lastRatesRecycler = view.findViewById(R.id.lastRatesRecycler)

        lastRatesRecycler.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = RecyclerAdapter(datesList, baseRate, valueList)
        }

    }

    fun addToList(date: String){
        datesList.add(date)
    }


}