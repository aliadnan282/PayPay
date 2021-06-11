package com.starter.repository

import com.starter.database.dao.CurrencyDao
import com.starter.database.entity.Currency
import com.starter.network.RemoteDataService
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiService: RemoteDataService,
    private val currencyDao: CurrencyDao
) : BaseRepository {

    // Remote-> Retrofit
    suspend fun getCurrentExchangeRates() = apiService.getCurrentExchangeRates()
    suspend fun getCurrencyTypes() = apiService.getCurrencyTypes()

    // Local-> Room
    suspend fun getSavedExchangeRates(): List<Currency> = currencyDao.getAllList()
    suspend fun updateAllExchangeRates(saveList: List<Currency>) =
        currencyDao.updateAllRate(saveList)
}