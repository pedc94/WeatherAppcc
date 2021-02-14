package com.example.weatherapp.data.api.implementations

import android.util.Log
import com.example.weatherapp.data.api.interfaces.IWeatherService
import com.example.weatherapp.data.api.interfaces.retrofit.IRWeatherService
import com.example.weatherapp.data.api.model.Forecast
import com.example.weatherapp.helpers.APIKEY
import com.example.weatherapp.helpers.UNITS
import retrofit2.Retrofit
import java.lang.IllegalArgumentException

class WeatherServiceImpl(private val retrofit : Retrofit) : IWeatherService {
    override suspend fun getForecast(city : String): Forecast? {
        val call = retrofit.create(IRWeatherService::class.java).getForecast(city, UNITS, APIKEY)
        if (call.isSuccessful){
            return call.body()
        }
        else {
            throw IllegalArgumentException(call.errorBody().toString())
        }
        //return call.getForecast(city, UNITS, APIKEY).body()
    }
}