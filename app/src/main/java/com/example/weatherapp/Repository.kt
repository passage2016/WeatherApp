package com.example.weatherapp

import com.example.weatherapp.model.remote.data.forecast.ForecastResponse
import com.example.weatherapp.model.remote.data.weather.WeatherResponse
import retrofit2.Response

interface Repository {
    suspend fun getWeather(city: String, apikey: String): Response<WeatherResponse>
    suspend fun getForecast(city: String, apikey: String): Response<ForecastResponse>
}