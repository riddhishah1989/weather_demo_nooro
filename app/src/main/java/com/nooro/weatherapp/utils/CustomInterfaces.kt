package com.nooro.weatherapp.utils

import com.nooro.weatherapp.data.model.WeatherDataModel

object CustomInterfaces {
    interface OnSearchedCityItemClick {
        fun onItemClick(data: WeatherDataModel)
    }
}