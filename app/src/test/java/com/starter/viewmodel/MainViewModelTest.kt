package com.starter.viewmodel

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.starter.MainCoroutinesRule
import com.starter.database.dao.CurrencyDao
import com.starter.network.RemoteDataService
import com.starter.preferences.AppPreferences
import com.starter.repository.AppRepository
import com.starter.utils.MockUtils
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
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