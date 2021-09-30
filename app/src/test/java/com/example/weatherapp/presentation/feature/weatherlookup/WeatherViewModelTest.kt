package com.example.weatherapp.presentation.feature.weatherlookup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapp.core.utils.NetworkResult
import com.example.weatherapp.data.api.interfaces.IWeatherService
import com.example.weatherapp.data.api.model.*
import com.example.weatherapp.utils.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.MockitoKotlinException
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class WeatherViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel: WeatherViewModel
    private val repository: IWeatherService = mock()

    init {
        viewModel = WeatherViewModel(repository)
    }

    @Test
    fun `given repository returns response successful, then networkstate updates with weather data`() =
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
            val forecastResponse = Forecast(cod = "200",
                forecastList = listOf(forecastList),
                city = city,
                message = 200,
                cnt = 40)
            whenever(repository.getForecast(any())).thenReturn(forecastResponse)

            //When
            viewModel.fetchForecast("Atlanta")

            //Then
            val viewStateLoading = viewModel.data.getOrAwaitValueTest()
            assertThat(viewStateLoading).isInstanceOf(NetworkResult.Loading::class.java)

            val viewState = viewModel.data.getOrAwaitValueTest()
            assertThat(viewState).isInstanceOf(NetworkResult.Success::class.java)
            val cityName = (viewState as NetworkResult.Success).data?.get(0)?.cityName
            assertThat(cityName?.contentEquals("Atlanta")).isTrue()
        }

    @Test
    fun `given repository returns exception ,then networkstate updates with error`() = runBlocking {
        //Given
        whenever(repository.getForecast(any())).thenThrow(MockitoKotlinException("Error simulator",
            Exception()))

        //When
        viewModel.fetchForecast("Atlanta")

        //Then
        val viewStateLoading = viewModel.data.getOrAwaitValueTest()
        assertThat(viewStateLoading).isInstanceOf(NetworkResult.Loading::class.java)

        val viewState = viewModel.data.getOrAwaitValueTest()
        assertThat(viewState).isInstanceOf(NetworkResult.Error::class.java)
    }

    @Test
    fun `when resetData(), then networkstate value is null `() {
        //Given
        //When
        viewModel.resetData()

        //Then
        val viewState = viewModel.data.getOrAwaitValueTest()
        assertThat(viewState).isNull()
    }
}