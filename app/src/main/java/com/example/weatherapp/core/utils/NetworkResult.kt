package com.example.weatherapp.core.utils

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null,
) {
    data class Success<T>(private val successData: T?) : NetworkResult<T>(successData)
    data class Error<T>(private val errorMessage: String?, private val errorData: T? = null) :
        NetworkResult<T>(errorData, errorMessage)

    class Loading<T> : NetworkResult<T>()
}