package com.starter.repository

import com.starter.network.RemoteDataClient
import javax.inject.Inject

class AppRepository @Inject constructor(private val appClient: RemoteDataClient) : BaseRepository{
    suspend fun getList(){
        appClient.getList()
    }
}