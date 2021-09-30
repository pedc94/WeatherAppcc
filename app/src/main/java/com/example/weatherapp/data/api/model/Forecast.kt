package com.example.weatherapp.data.api.model

import com.squareup.moshi.Json

data class Forecast(
    @field:Json(name = "city") val city: City,
    @field:Json(name = "cnt") val cnt: Int,
    @field:Json(name = "cod") val cod: String,
    @field:Json(name = "list") val forecastList: List<ForecastList>,
    @field:Json(name = "message") val message: Int,
)