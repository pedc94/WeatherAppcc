package com.example.weatherapp.presentation.feature.weatherlookup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.core.utils.NetworkResult
import com.example.weatherapp.data.api.interfaces.IWeatherService
import com.example.weatherapp.domain.mappers.toDomain
import com.example.weatherapp.domain.model.ForecastModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherService: IWeatherService) :
    ViewModel() {

    private var _data = MutableLiveData<NetworkResult<List<ForecastModel>?>?>()
    val data: LiveData<NetworkResult<List<ForecastModel>?>?> get() = _data

    fun fetchForecast(city: String) {
        fetchService(city)
    }

    private fun fetchService(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _data.postValue(NetworkResult.Loading())

            try {
                val forecastList = weatherService.getForecast(city)

                if (forecastList?.forecastList?.size!! > 0) {
                    val modelList =
                        forecastList.forecastList.map { it.toDomain(city) }
                    _data.postValue(NetworkResult.Success(modelList))
                } else
                    _data.postValue(NetworkResult.Error("City not found", null))
            } catch (e: Exception) {
                _data.postValue(NetworkResult.Error(e.toString(), null))
            }
        }
    }

    fun resetData() {
        _data.postValue(null)
    }
}