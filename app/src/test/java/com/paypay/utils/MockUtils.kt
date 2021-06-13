package com.paypay.utils

import com.paypay.database.entity.Currency
import com.paypay.model.CurrencyTypes

object MockUtils {

    private fun mockCurrency() = Currency("PKR", "Pakistan", 150.0, "", 0)
    private fun mockCurrencyType() = CurrencyTypes(mapOf("PKR" to "Pakistan"))
    fun mockCurrencyList() = arrayListOf<Currency>(mockCurrency())
    fun mockCurrencyTypes() = mockCurrencyType()
}