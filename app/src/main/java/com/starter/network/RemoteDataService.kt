package com.starter.network

import retrofit2.http.GET

interface RemoteDataService {
    @GET("/abc")
    fun getList()
}