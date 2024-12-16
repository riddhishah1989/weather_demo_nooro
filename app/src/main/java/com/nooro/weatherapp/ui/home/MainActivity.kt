package com.nooro.weatherapp.ui.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nooro.weatherapp.R
import com.nooro.weatherapp.data.model.WeatherDataModel
import com.nooro.weatherapp.data.remote.api.APIResponse
import com.nooro.weatherapp.databinding.ActivityMainBinding
import com.nooro.weatherapp.ui.customView.CustomProgressDialog
import com.nooro.weatherapp.utils.AppConstants
import com.nooro.weatherapp.utils.SharedPreferencesUtils
import com.nooro.weatherapp.utils.Utils
import com.nooro.weatherapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var progressDialog: CustomProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        progressDialog = CustomProgressDialog(this)
        init()
    }

    private fun init() {
        loadDataFromPrefs()
        observeWeatherData()
        binding.edtSearchCity.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = textView.text.toString()
                if (query.isNotEmpty()) {
                    Utils.hideKeyboard(this)
                    progressDialog.show()
                    viewModel.getWeatherDataByCity(query)
                    binding.rlWeatherStoredData.visibility = View.GONE
                }
                true
            } else {
                false
            }
        }
        binding.llWeatherDetails.setOnClickListener {
            SharedPreferencesUtils.saveData(AppConstants.PREF_WEATHER_MODEL, binding.weatherModel)
        }
    }

    private fun loadDataFromPrefs() {
        //if its stored then it will show the data from the sharedpreference
        val data = SharedPreferencesUtils.getData(
            AppConstants.PREF_WEATHER_MODEL,
            WeatherDataModel::class.java
        )
        if (data != null) {
            binding.weatherModel = data
            binding.isAPICalled = false
            binding.temprature = data.temperature.roundToInt().toString()
            binding.edtSearchCity.setText(data.cityName)
        }
    }

    private fun observeWeatherData() {
        viewModel.getWeatherByCity.observe(this) { weatherResponse ->
            progressDialog.dismiss()
            binding.isAPICalled = true
            when (weatherResponse) {
                is APIResponse.Success -> {
                    weatherResponse.data.apply {
                        weatherIcon = Utils.getFullUrl(weatherIcon)
                    }
                    binding.weatherModel = weatherResponse.data
                    binding.temprature = weatherResponse.data.temperature.roundToInt().toString()
                }

                is APIResponse.Error -> {
                    Utils.showToast(this, weatherResponse.message)
                }
            }
        }
    }
}