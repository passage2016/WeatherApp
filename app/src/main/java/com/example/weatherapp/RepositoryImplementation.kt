package com.example.weatherapp

import com.example.weatherapp.model.remote.ApiServer
import com.example.weatherapp.model.remote.data.forecast.ForecastResponse
import com.example.weatherapp.model.remote.data.weather.WeatherResponse
import retrofit2.Response

class RepositoryImplementation(val apiServer: ApiServer): Repository {
    override suspend fun getWeather(city: String, apikey: String): Response<WeatherResponse> {
        return apiServer.getWeather(city, appid = apikey)
    }

    override suspend fun getForecast(city: String, apikey: String): Response<ForecastResponse> {
        return apiServer.getForecast(city, appid = apikey)
    }
}