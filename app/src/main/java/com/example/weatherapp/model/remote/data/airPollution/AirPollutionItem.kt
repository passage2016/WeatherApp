package com.example.weatherapp.model.remote.data.airPollution

data class AirPollutionItem(
    val components: Components,
    val dt: Int,
    val main: Main
)