package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.weatherapp.data.api.interfaces.IWeatherService
import com.example.weatherapp.data.api.model.ForecastList
import kotlinx.coroutines.Dispatchers
import com.example.weatherapp.data.api.model.Forecast
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherService : IWeatherService) : ViewModel() {
   private val _forecastList  = MutableLiveData<Forecast?>()
    val forecastList : LiveData<Forecast?> get() = _forecastList
    private val _loadError = MutableLiveData<Boolean>()
    val loadError : LiveData<Boolean> get() = _loadError
    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> get() = _loading

    fun fetchForecast(city : String) {
        fetchService(city)
    }
    private fun fetchService(city: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                _forecastList.value = weatherService.getForecast(city)
                _loadError.value = false
                _loading.value = false
            }
            catch (exception: Exception) {
                _loadError.value = true
                _loading.value = false
                exception.printStackTrace()
            }

        }
    }
}