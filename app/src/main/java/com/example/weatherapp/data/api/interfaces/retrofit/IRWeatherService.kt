package com.example.weatherapp.data.api.interfaces.retrofit

import com.example.weatherapp.data.api.model.Forecast
import com.example.weatherapp.helpers.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IRWeatherService {
    @GET(ENDPOINT)
    suspend fun getForecast(@Query("q") city : String, @Query("units") units : String, @Query("appid") key : String): Response<Forecast?>

}