package com.paypay.network

import com.paypay.helper.AppConstants
import okhttp3.Interceptor
import okhttp3.Response

class HttpRequestInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val url = originalRequest.url.newBuilder().addQueryParameter("access_key", AppConstants.ACCESS_KEY)
      .addQueryParameter("format","1").build()
    val request = originalRequest.newBuilder().url(url).build()
    return chain.proceed(request)
  }
}
