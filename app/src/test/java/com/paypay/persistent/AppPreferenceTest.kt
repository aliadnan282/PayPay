package com.paypay.persistent

import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.paypay.helper.AppConstants
import com.paypay.preferences.AppPreferences
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AppPreferenceTest {
    private var preference: AppPreferences = mock()

    @Test
    fun check_minute_persistent(){
        whenever(preference.getLong("time")).thenReturn(AppConstants.MINUTE_30)
        Assert.assertEquals(preference.getLong("time"), AppConstants.MINUTE_30)
        verify(preference, atLeastOnce()).getLong("time")
    }
}