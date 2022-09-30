package com.example.weatherapp

import com.example.weatherapp.model.remote.ApiServer
import com.example.weatherapp.model.remote.data.airPollution.AirPollutionResponse
import com.example.weatherapp.model.remote.data.forecast.ForecastResponse
import com.example.weatherapp.model.remote.data.weather.WeatherResponse
import retrofit2.Response

class RepositoryImplementation(val apiServer: ApiServer): Repository {
    override suspend fun getWeather(city: String, apikey: String): Response<WeatherResponse> {
        return apiServer.getWeather(city, appid = apikey)
    }

    override suspend fun getWeather(lat: Double, lon: Double, apikey: String): Response<WeatherResponse> {
        return apiServer.getWeather(lat = lat, lon = lon, appid = apikey)
    }

    override suspend fun getForecast(lat: Double, lon: Double, apikey: String): Response<ForecastResponse> {
        return apiServer.getForecast(lat = lat, lon = lon, appid = apikey)
    }

    override suspend fun getAirPollution(lat: Double, lon: Double, apikey: String): Response<AirPollutionResponse> {
        return apiServer.getAirPollution(lat = lat, lon = lon, appid = apikey)
    }
}