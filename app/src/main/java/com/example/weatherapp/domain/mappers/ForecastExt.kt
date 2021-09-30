package com.example.weatherapp.domain.mappers

import com.example.weatherapp.data.api.model.ForecastList
import com.example.weatherapp.domain.model.ForecastModel

fun ForecastList.toDomain(city: String): ForecastModel = ForecastModel(
    dateTime = dateTime,
    cityName = city,
    temperature = main.temperature,
    feelsLike = main.feelsLike,
    weatherName = weatherList.first().main,
    weatherDescription = weatherList.first().description
)