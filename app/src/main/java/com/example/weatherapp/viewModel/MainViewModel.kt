package com.example.weatherapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Repository
import com.example.weatherapp.model.remote.data.forecast.ForecastResponse
import com.example.weatherapp.model.remote.data.weather.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val weatherLiveData = MutableLiveData<WeatherResponse>()
    val forecastLiveData = MutableLiveData<ForecastResponse>()


    fun getWeather(city: String, apikey: String) = viewModelScope.launch{
        val response = repository.getWeather(city, apikey)
        if (!response.isSuccessful) {
            return@launch
        }
        response.body()?.let {
            weatherLiveData.postValue(it)
        }
    }

    fun getForecast(city: String, apikey: String) = viewModelScope.launch{
        val response = repository.getForecast(city, apikey)
        if (!response.isSuccessful) {
            return@launch
        }
        response.body()?.let {
            forecastLiveData.postValue(it)
        }

    }


}