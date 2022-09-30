package com.example.weatherapp.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ViewAirPollutionBinding
import com.example.weatherapp.databinding.ViewForecastBinding
import com.example.weatherapp.model.remote.data.airPollution.AirPollutionItem
import com.example.weatherapp.model.remote.data.forecast.WeatherDetail

class AirPollutionAdapter(val context: Context, private val weather: List<AirPollutionItem>) :
    RecyclerView.Adapter<AirPollutionAdapter.AirPollutionHolder>() {

    override fun getItemCount(): Int {
        return weather.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirPollutionHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewAirPollutionBinding>(
            layoutInflater,
            R.layout.view_air_pollution,
            parent,
            false
        )
        return AirPollutionHolder(binding)
    }

    override fun onBindViewHolder(holder: AirPollutionHolder, position: Int) {
        holder.setData(weather[position])
    }

    inner class AirPollutionHolder(private val binding: ViewAirPollutionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(airPollutionItem: AirPollutionItem) {
            binding.airPollutionItem = airPollutionItem
        }
    }
}