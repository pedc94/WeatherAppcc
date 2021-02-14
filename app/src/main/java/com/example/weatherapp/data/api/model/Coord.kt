package com.example.weatherapp.data.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coord(
    val lat: Double,
    val lon: Double
):Parcelable