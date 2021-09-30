package com.example.weatherapp.core.di

import com.example.weatherapp.data.api.implementations.WeatherRepositoryImpl
import com.example.weatherapp.data.api.interfaces.IWeatherService
import com.example.weatherapp.data.api.interfaces.retrofit.IRWeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesRandomRepository(weatherApi: IRWeatherService): IWeatherService =
        WeatherRepositoryImpl(weatherApi)
}