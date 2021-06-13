package com.paypay.network

import com.paypay.model.CurrencyRates
import com.paypay.model.CurrencyTypes
import retrofit2.http.GET

interface RemoteDataService {

    @GET("live")
    suspend fun getCurrentExchangeRates(): CurrencyRates

    @GET("list")
    suspend fun getCurrencyTypes(): CurrencyTypes
}