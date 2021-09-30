package com.example.weatherapp.data.api

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.api.implementations.WeatherRepositoryImpl
import com.example.weatherapp.data.api.interfaces.IWeatherService
import com.example.weatherapp.data.api.interfaces.retrofit.IRWeatherService
import com.example.weatherapp.data.api.model.*
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test

class WeatherRepositoryImplTest {
    private val weatherApi: IRWeatherService = mock()
    private val weatherRepository: IWeatherService by lazy { WeatherRepositoryImpl(weatherApi) }

    @Test
    fun `given api uses to getForecast() with cityname correctly , then return response successful with Weather data  and code 200`() =
        runBlocking {
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
            val forecast = Forecast(cod = "200",
                forecastList = listOf(forecastList),
                city = city,
                message = 200,
                cnt = 40)

            //When
            whenever(weatherApi.getForecast("Atlanta", BuildConfig.API_KEY, "metric")).thenReturn(
                forecast)
            val forecastResponse = weatherRepository.getForecast("Atlanta")

            //Then
            assertThat(forecastResponse?.cod?.contentEquals("200")).isTrue()
            assertThat(forecastResponse?.city?.name?.contentEquals("Atlanta")).isTrue()

        }
}