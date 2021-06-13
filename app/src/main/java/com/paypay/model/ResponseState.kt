package com.paypay.model

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ResponseState<out T> {

    data class  Loading (val loading: Boolean): ResponseState<Nothing>()
    data class Success<T>(val data: T) : ResponseState<T>()
    data class Error(val exception: String?) : ResponseState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading=$loading"
        }
    }
}