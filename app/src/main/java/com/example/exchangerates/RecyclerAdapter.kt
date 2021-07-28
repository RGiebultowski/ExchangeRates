package com.example.exchangerates


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private var dates: List<String>, private var baseRate: String, private var value: List<String>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val itemDates: TextView = itemView.findViewById(R.id.dateRate)
            val itemBaseRate: TextView = itemView.findViewById(R.id.baseRate)
            val itemValue: TextView = itemView.findViewById(R.id.values)

            init {
                itemView.setOnClickListener {
                        v: View -> val position: Int = adapterPosition
                    Toast.makeText(itemView.context, "Rates from day ${itemDates.text}", Toast.LENGTH_SHORT).show()

                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemDates.text = dates[position]
        holder.itemBaseRate.text = baseRate
        holder.itemValue.text = value.toString()
    }

    override fun getItemCount(): Int {
        return dates.size
    }
}