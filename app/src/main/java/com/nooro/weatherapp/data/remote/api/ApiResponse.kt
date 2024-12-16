package com.nooro.weatherapp.data.remote.api


sealed class APIResponse<T> {
    class Success<T>(val data: T) : APIResponse<T>()
    class Error<T>(val message: String) : APIResponse<T>()
}
