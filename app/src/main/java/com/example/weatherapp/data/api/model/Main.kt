package com.example.weatherapp.data.api.model

import com.squareup.moshi.Json

data class Main(
    @field:Json(name = "feels_like") val feelsLike: Double,
    @field:Json(name = "grnd_level") val groundLevel: Int,
    @field:Json(name = "humidity") val humidity: Int,
    @field:Json(name = "pressure") val pressure: Int,
    @field:Json(name = "sea_level") val seaLevel: Int,
    @field:Json(name = "temp") val temperature: Double,
    @field:Json(name = "temp_kf") val tempKf: Double,
    @field:Json(name = "temp_max") val tempMax: Double,
    @field:Json(name = "temp_min") val tempMin: Double,
)