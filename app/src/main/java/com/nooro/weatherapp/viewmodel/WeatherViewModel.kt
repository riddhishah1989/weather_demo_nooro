package com.nooro.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nooro.weatherapp.MyApplication
import com.nooro.weatherapp.R
import com.nooro.weatherapp.data.model.WeatherDataModel
import com.nooro.weatherapp.data.remote.api.APIResponse
import com.nooro.weatherapp.data.repository.WeatherRepository
import com.nooro.weatherapp.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val apiRepository: WeatherRepository
) : BaseViewModel() {

    private val application = MyApplication.instance

    private val _getWeatherByCity = MutableLiveData<APIResponse<WeatherDataModel>>()
    val getWeatherByCity: LiveData<APIResponse<WeatherDataModel>> = _getWeatherByCity

    fun getWeatherDataByCity(cityName: String) {
        viewModelScope.launch {
            if (Utils.isInternetConnected(application)) {
                showLoading()
                val result = apiRepository.getWeatherByCityName(cityName)
                _getWeatherByCity.postValue(result)
                hideLoading()
            } else {
                return@launch Utils.showToast(
                    application,
                    application.getString(R.string.no_internet_connection_found)
                )
            }
        }
    }
}
