package com.example.swiss.network.utils

import android.util.Log
import androidx.compose.runtime.Composable

sealed class ResponseWrapper<out T> {

    class Success<T>(val data: T) : ResponseWrapper<T>()

    class Error(val exception: java.lang.Exception) : ResponseWrapper<Nothing>()

    @Composable
    fun onSuccess(result : @Composable (T)->Unit): ResponseWrapper<T> {
        if(this is Success) result(data)
        return this
    }

    @Composable
    fun onError(result : @Composable (Exception)->Unit): ResponseWrapper<T> {

        if(this is Error) {
            Log.d("ERRORE", exception.message ?: "")
            result(exception)
        }
        return this
    }
}