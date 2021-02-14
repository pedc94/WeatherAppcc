package com.example.weatherapp.data.api.interfaces

import com.example.weatherapp.data.api.model.Forecast

interface IWeatherService {
    suspend fun  getForecast(city : String) : Forecast?
}