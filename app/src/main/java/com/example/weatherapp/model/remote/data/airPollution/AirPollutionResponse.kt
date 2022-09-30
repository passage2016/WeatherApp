package com.example.weatherapp.model.remote.data.airPollution

data class AirPollutionResponse(
    val coord: Coord,
    val list: List<AirPollutionItem>
)