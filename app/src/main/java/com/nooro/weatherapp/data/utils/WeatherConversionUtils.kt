package com.nooro.weatherapp.data.utils

import com.nooro.weatherapp.data.remote.webResponse.WeatherResponse
import com.nooro.weatherapp.data.model.WeatherDataModel

// Conversion function from WeatherResponse to WeatherData
fun WeatherResponse.toWeatherData() = WeatherDataModel(
    cityName = location.name,
    temperature = current.temp_c,
    feelsLike = current.feelslike_c,
    humidity = current.humidity,
    weatherCondition = current.condition.text,
    weatherIcon = current.condition.icon,
    windSpeed = current.wind_kph,
    windDirection = current.wind_dir,
    uvIndex = current.uv,
    pressure = current.pressure_mb,
    visibility = current.vis_km
)