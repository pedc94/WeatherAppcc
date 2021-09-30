package com.example.weatherapp.data.api.interfaces.retrofit

import com.example.weatherapp.data.api.model.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface IRWeatherService {
    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String, @Query("appid") key: String,
        @Query("units") units: String = "metric",
    ): Forecast?
}