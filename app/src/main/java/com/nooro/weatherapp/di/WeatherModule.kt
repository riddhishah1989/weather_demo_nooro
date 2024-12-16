package com.nooro.weatherapp.di

import com.google.gson.GsonBuilder
import com.nooro.weatherapp.data.remote.api.APIServices
import com.nooro.weatherapp.data.remote.api.ApiConstants
import com.nooro.weatherapp.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIServices {
        return retrofit.create(APIServices::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(apiServices: APIServices): WeatherRepository {
        return WeatherRepository(apiServices)
    }
}