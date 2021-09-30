package com.example.weatherapp.data.api.model

import com.squareup.moshi.Json


data class City(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "coord") val coord: Coord,
    @field:Json(name = "country") val country: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "population") val population: Int,
    @field:Json(name = "sunrise") val sunrise: Int,
    @field:Json(name = "sunset") val sunset: Int,
    @field:Json(name = "timezone") val timeZone: Int,
)