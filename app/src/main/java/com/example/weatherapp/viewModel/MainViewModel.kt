package com.example.weatherapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Repository
import com.example.weatherapp.model.remote.data.airPollution.AirPollutionResponse
import com.example.weatherapp.model.remote.data.forecast.ForecastResponse
import com.example.weatherapp.model.remote.data.weather.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val weatherLiveData = MutableLiveData<WeatherResponse>()
    val forecastLiveData = MutableLiveData<ForecastResponse>()
    val airPollutionLiveData = MutableLiveData<AirPollutionResponse>()

    fun getWeather(city: String, apikey: String) = viewModelScope.launch{
        val response = repository.getWeather(city, apikey)
        if (!response.isSuccessful) {
            return@launch
        }
        response.body()?.let {
            weatherLiveData.postValue(it)
        }
    }

    fun getWeather(lat: Double, lon: Double, apikey: String) = viewModelScope.launch{
        val response = repository.getWeather(lat, lon, apikey)
        if (!response.isSuccessful) {
            return@launch
        }
        response.body()?.let {
            weatherLiveData.postValue(it)
        }
    }

    fun getForecast(lat: Double, lon: Double, apikey: String) = viewModelScope.launch{
        val response = repository.getForecast(lat, lon, apikey)
        if (!response.isSuccessful) {
            return@launch
        }
        response.body()?.let {
            forecastLiveData.postValue(it)
        }

    }

    fun getAirPollution(lat: Double, lon: Double, apikey: String) = viewModelScope.launch{
        val response = repository.getAirPollution(lat, lon, apikey)
        if (!response.isSuccessful) {
            return@launch
        }
        response.body()?.let {
            airPollutionLiveData.postValue(it)
        }

    }
}