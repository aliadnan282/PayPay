package com.paypay.viewmodel

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.paypay.MainCoroutinesRule
import com.paypay.database.dao.CurrencyDao
import com.paypay.network.RemoteDataService
import com.paypay.preferences.AppPreferences
import com.paypay.repository.AppRepository
import com.paypay.utils.MockUtils
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    private lateinit var viewModel: CurrencyViewModel
    private lateinit var appRepository: AppRepository
    private val remoteDataService: RemoteDataService = mock()
    private val appPreferences: AppPreferences = mock()
    private val currencyDao: CurrencyDao = mock()

    @get:Rule
    val coroutineRule = MainCoroutinesRule()

    @Before
    fun initRepository(){
        appRepository = AppRepository(remoteDataService, currencyDao)
        viewModel = CurrencyViewModel(appRepository, appPreferences)
    }

    @Test
    fun getCurrencyList() = runBlocking {
        val mockList = MockUtils.mockCurrencyList()
        whenever(currencyDao.getAllList()).thenReturn(mockList)
        val list = appRepository.getSavedExchangeRates()
        Assert.assertEquals(list.size, mockList.size)
    }
}