package com.nooro.weatherapp

import android.app.Application
import com.nooro.weatherapp.utils.SharedPreferencesUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        SharedPreferencesUtils.init(this)
    }
}