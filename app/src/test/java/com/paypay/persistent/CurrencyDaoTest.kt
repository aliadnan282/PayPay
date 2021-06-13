package com.paypay.persistent

import com.paypay.database.dao.CurrencyDao
import com.paypay.utils.MockUtils
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CurrencyDaoTest : LocalDatabase(){

    lateinit var currencyDao: CurrencyDao

    @Before
    fun initDao(){
        currencyDao = db.getCurrencyDao()
    }

    @Test
    fun insertAndLoadCurrency() = runBlocking {
        val currencyList = MockUtils.mockCurrencyList()
        currencyDao.updateAllRate(currencyList)
        val dbList = currencyDao.getAllList()

        MatcherAssert.assertThat(currencyList.toString(), CoreMatchers.`is`(dbList.toString()))
    }
}