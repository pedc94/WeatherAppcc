package com.example.weatherapp.data.api.model

import com.squareup.moshi.Json

data class Wind(
    @field:Json(name = "deg") val deg: Int,
    @field:Json(name = "speed") val speed: Double,
)