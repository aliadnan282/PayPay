package com.starter.network

import android.util.Log
import com.starter.helper.AppConstants
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class HttpRequestInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val url = originalRequest.url.newBuilder().addQueryParameter("access_key", AppConstants.ACCESS_KEY)
      .addQueryParameter("format","1").build()
    val request = originalRequest.newBuilder().url(url).build()
    return chain.proceed(request)
  }
}
