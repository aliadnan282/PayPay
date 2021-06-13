package com.paypay.repository

import com.nhaarman.mockitokotlin2.*
import com.paypay.MainCoroutinesRule
import com.paypay.database.dao.CurrencyDao
import com.paypay.network.RemoteDataService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AppRepositoryTest {
    private lateinit var repository: AppRepository
    private val service: RemoteDataService = mock()
    private val currencyDao: CurrencyDao = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        repository = AppRepository(service, currencyDao)
    }

    @Test
    fun fetchCurrencyFromNetworkTest(): Unit = runBlocking {

        whenever(repository.getSavedExchangeRates()).thenReturn(null)
        whenever(repository.getCurrentExchangeRates()).thenReturn(null)
        repository.getSavedExchangeRates()
        repository.getCurrentExchangeRates()
        verify(currencyDao, atLeastOnce()).getAllList()
        verify(service, atLeastOnce()).getCurrentExchangeRates()
        verifyNoMoreInteractions(service)
    }

}