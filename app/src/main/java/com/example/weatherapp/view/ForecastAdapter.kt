package com.example.weatherapp.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ViewForecastBinding
import com.example.weatherapp.model.remote.data.forecast.WeatherDetail

class ForecastAdapter(val context: Context, private val weather: List<WeatherDetail>) :
    RecyclerView.Adapter<ForecastAdapter.WeatherViewHolder>() {

    override fun getItemCount(): Int {
        return weather.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewForecastBinding>(
            layoutInflater,
            R.layout.view_forecast,
            parent,
            false
        )
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.setData(weather[position])
    }

    inner class WeatherViewHolder(private val binding: ViewForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(weather: WeatherDetail) {
            binding.weather = weather
        }
    }
}