package com.example.weatherapp.data.api.implementations

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.api.interfaces.IWeatherService
import com.example.weatherapp.data.api.interfaces.retrofit.IRWeatherService
import com.example.weatherapp.data.api.model.Forecast

class WeatherRepositoryImpl(private val weatherService: IRWeatherService) : IWeatherService {
    override suspend fun getForecast(city: String): Forecast? {
        return weatherService.getForecast(city = city, key = BuildConfig.API_KEY)
    }
}