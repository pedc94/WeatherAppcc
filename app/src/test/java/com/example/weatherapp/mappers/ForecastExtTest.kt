package com.example.weatherapp.mappers

import com.example.weatherapp.data.api.model.*
import com.example.weatherapp.domain.mappers.toDomain
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ForecastExtTest {
    @Test
    fun `convert ForecastList To ForecastModel,then verify fields matches correctly`() {
        //Given
        val main = Main(35.0, 35, 34, 36, 100, 34.6, 79.0, 38.0, 32.0)
        val coord = Coord(123456789.3, 1234788755.0)
        val city = City(1, coord, "United States", "Atlanta", 2455782, 7, 19, 4)
        val clouds = Clouds(20)
        val rain = Rain(10.2)
        val sys = Sys("sys")
        val weather = Weather("cloudy", "", 10, "clouds")
        val wind = Wind(10, 7.2)
        val forecastList = ForecastList(clouds,
            13356475,
            "Sep 28",
            main,
            200000.0,
            rain,
            sys,
            10,
            listOf(weather),
            wind)
        val forecastResponse =
            Forecast(cod = "200", forecastList = listOf(forecastList), city = city, message = 200, cnt = 40)

        //When
        val weatherView = forecastResponse.forecastList[0].toDomain("Atlanta")

        //Match and validate Fields
        //Then
        assertThat(weatherView.dateTime == 13356475).isTrue()
        assertThat(weatherView.cityName.contentEquals("Atlanta")).isTrue()
        assertThat(weatherView.temperature.equals(34.6)).isTrue()
        assertThat(weatherView.feelsLike.equals(35.0)).isTrue()
        assertThat(weatherView.weatherName.isEmpty())
        assertThat(weatherView.weatherDescription == "cloudy").isTrue()

    }

}