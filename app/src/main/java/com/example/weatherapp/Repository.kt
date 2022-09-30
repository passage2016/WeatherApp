package com.example.weatherapp

import com.example.weatherapp.model.remote.data.airPollution.AirPollutionResponse
import com.example.weatherapp.model.remote.data.forecast.ForecastResponse
import com.example.weatherapp.model.remote.data.weather.WeatherResponse
import retrofit2.Response

interface Repository {
    suspend fun getWeather(city: String, apikey: String): Response<WeatherResponse>
    suspend fun getWeather(at: Double, lon: Double, apikey: String): Response<WeatherResponse>
    suspend fun getForecast(lat: Double, lon: Double, apikey: String): Response<ForecastResponse>
    suspend fun getAirPollution(lat: Double, lon: Double, apikey: String): Response<AirPollutionResponse>
}