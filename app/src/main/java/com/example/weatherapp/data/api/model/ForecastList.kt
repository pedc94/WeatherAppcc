package com.example.weatherapp.data.api.model

import com.squareup.moshi.Json

data class ForecastList(
    @field:Json(name = "clouds") val clouds: Clouds,
    @field:Json(name = "dt") val dateTime: Int,
    @field:Json(name = "dt_txt") val dateText: String,
    @field:Json(name = "main") val main: Main,
    @field:Json(name = "pop") val pop: Double,
    @field:Json(name = "rain") val rain: Rain,
    @field:Json(name = "sys") val sys: Sys,
    @field:Json(name = "visibility") val visibility: Int,
    @field:Json(name = "weather") val weatherList: List<Weather>,
    @field:Json(name = "wind") val wind: Wind,
)