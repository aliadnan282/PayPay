package com.starter.repository

import com.nhaarman.mockitokotlin2.*
import com.starter.MainCoroutinesRule
import com.starter.database.dao.CurrencyDao
import com.starter.network.RemoteDataService
import com.starter.utils.MockUtils
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