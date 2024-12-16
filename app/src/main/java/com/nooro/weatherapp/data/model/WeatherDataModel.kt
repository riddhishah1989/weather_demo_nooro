package com.nooro.weatherapp.data.model

data class WeatherDataModel(
    val cityName: String,
    var temperature: Double,
    val feelsLike: Double,
    val humidity: Int,
    val weatherCondition: String,
    var weatherIcon: String,
    val windSpeed: Double,
    val windDirection: String,
    val uvIndex: Double,
    val pressure: Double,
    val visibility: Double
)