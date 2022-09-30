package com.example.weatherapp.model.remote

import com.example.weatherapp.model.remote.Constants.UNITS
import com.example.weatherapp.model.remote.data.airPollution.AirPollutionResponse
import com.example.weatherapp.model.remote.data.forecast.ForecastResponse
import com.example.weatherapp.model.remote.data.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServer {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") appid: String,
        @Query("units") units: String = UNITS
    ): Response<WeatherResponse>

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") units: String = UNITS
    ): Response<WeatherResponse>

    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") units: String = UNITS
    ): Response<ForecastResponse>

    @GET("data/2.5/air_pollution/forecast")
    suspend fun getAirPollution(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
    ): Response<AirPollutionResponse>
}