package com.example.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastModel(
    val dateTime: Int = 0,
    val cityName: String = "",
    val temperature: Double = 0.0,
    val feelsLike: Double = 0.0,
    val weatherName: String = "",
    val weatherDescription: String = "",

    ) : Parcelable {
}