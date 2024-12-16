package com.nooro.weatherapp.data.repository

import com.nooro.weatherapp.MyApplication
import com.nooro.weatherapp.R
import com.nooro.weatherapp.data.remote.api.APIResponse
import com.nooro.weatherapp.data.remote.api.APIServices
import com.nooro.weatherapp.data.utils.toWeatherData
import com.nooro.weatherapp.data.model.WeatherDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(private val apiServices: APIServices) {

    private val application = MyApplication.instance

    /**
     * Returns weather info by city
     */
    suspend fun getWeatherByCityName(cityName: String): APIResponse<WeatherDataModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiServices.getWeatherForecastByCity(cityName)
                if (response.isSuccessful) {
                    response.body()?.let {
                        APIResponse.Success(it.toWeatherData())
                    } ?: APIResponse.Error(application.getString(R.string.no_data_available))
                } else {
                    APIResponse.Error(application.getString(R.string.failed_to_fetch_weather))
                }
            } catch (e: Exception) {
                APIResponse.Error(application.getString(R.string.network_error, e.message))
            }
        }
    }

}
