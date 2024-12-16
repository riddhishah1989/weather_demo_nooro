package com.nooro.weatherapp.data.remote.api

import com.nooro.weatherapp.data.remote.webResponse.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {
    @GET("current.json?")
    suspend fun getWeatherForecastByCity(
        @Query("q") q: String,
        @Query("key") key: String = ApiConstants.WEATHER_API_KEY,
        @Query("aqi") aqi: String = "no"
    ): Response<WeatherResponse>

}