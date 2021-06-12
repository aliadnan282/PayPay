package com.starter.network

import android.service.autofill.FieldClassification
import com.starter.model.ResponseState
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ApiResponseTest {

    @Test
    fun test_error_response(){
        val exception = Exception("error")
        val response = ResponseState.Error(exception.message)
        MatcherAssert.assertThat(response.exception, `is`("error"))
    }

    @Test
    fun test_successful_response(){
        val  apiResponse = ResponseState.Success("successful")
            MatcherAssert.assertThat(apiResponse.data, CoreMatchers.`is`("successful"))
    }
}