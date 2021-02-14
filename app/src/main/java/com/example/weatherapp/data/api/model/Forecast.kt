package com.example.weatherapp.data.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastList>,
    val message: Int
) :Parcelable