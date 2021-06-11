package com.starter.network

import com.starter.model.CurrencyRates
import com.starter.model.CurrencyTypes
import retrofit2.http.GET

interface RemoteDataService {

    @GET("live")
    suspend fun getCurrentExchangeRates(): CurrencyRates

    @GET("list")
    suspend fun getCurrencyTypes(): CurrencyTypes
}