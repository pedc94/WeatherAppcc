package com.example.weatherapp.data.api.model

import com.squareup.moshi.Json

data class Rain(
    @field:Json(name = "3h") val threeH: Double,
)