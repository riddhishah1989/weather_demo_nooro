package com.nooro.weatherapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

object SharedPreferencesUtils {

    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    private const val PREFS_NAME = "my_prefs"


    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun <T> saveData(key: String, data: T) {
        val json = gson.toJson(data) // Convert object to JSON
        sharedPreferences.edit().putString(key, json).apply()
    }

    fun <T> getData(key: String, type: Class<T>): T? {
        val json = sharedPreferences.getString(key, null)
        return if (json != null) {
            gson.fromJson(json, type)
        } else {
            null
        }
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}
