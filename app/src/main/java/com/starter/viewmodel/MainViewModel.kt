package com.starter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.starter.database.entity.Currency
import com.starter.helper.AppConstants
import com.starter.model.CurrencyRates
import com.starter.model.CurrencyTypes
import com.starter.model.ResponseState
import com.starter.preferences.AppPreferences
import com.starter.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var repository: AppRepository,
    private val preferences: AppPreferences
) : ViewModel() {

    fun getCurrencyData() = liveData(Dispatchers.IO) {
        emit(ResponseState.Loading(true))
        try {
            val period = 30 * 60 * 1000
            if ((preferences.getLong(AppConstants.TIME_STAMP)
                    .plus(period)) > getCurrentTimeStampInMillis()
            ) {
                emit(ResponseState.Success(data = repository.getSavedExchangeRates()))
            } else {
                val types = viewModelScope.async {
                    repository.getCurrencyTypes()
                }.await()
                val rates = viewModelScope.async {
                    repository.getCurrentExchangeRates()
                }.await()
                emit(ResponseState.Success(getCompoundCurrency(rates, types)))
                preferences.setLong(AppConstants.TIME_STAMP, rates.timestamp)
            }
        } catch (exception: Exception) {
            // Mostly this error thrown, that's why hardcode this text here, otherwise I have added ErrorResponse model
                // Note: --> in case error is continuously coming you can check logcate
            emit(ResponseState.Error("You have exceeded the maximum rate limitation allowed on your subscription plan. Please refer to the \\\"Rate Limits\\\" section of the API Documentation for details."))

        }
    }

    private fun getCurrentTimeStampInMillis(): Long {
        return System.currentTimeMillis() / 1000
    }


    private fun getCompoundCurrency(rates: CurrencyRates, types: CurrencyTypes): List<Currency> {
        val currencies = ArrayList<Currency>()
        for (entry in types.currencies) {
            val currency = Currency()
            currency.code = entry.key
            currency.name = entry.value
            currency.rate =
                rates.quotes[rates.source + currency.code]!! // make it -> USDINR
            currencies.add(currency)
        }
        return currencies
    }

    fun updateCurrencies(list: List<Currency>) = liveData(Dispatchers.IO) {
        emit(ResponseState.Loading(true))
        try {
            emit(ResponseState.Success(data = repository.updateAllExchangeRates(list)))
        } catch (exception: Exception) {
            emit(
                ResponseState.Error(
                    exception.message ?: "Error Occurred while updating database!"
                )
            )
        }
    }
}