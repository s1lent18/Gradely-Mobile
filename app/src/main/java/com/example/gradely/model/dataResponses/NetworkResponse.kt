package com.example.gradely.model.dataResponses

sealed class NetworkResponse<out T> {

    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Failure(val msg: String) : NetworkResponse<Nothing>()
    data object Loading : NetworkResponse<Nothing>()
}