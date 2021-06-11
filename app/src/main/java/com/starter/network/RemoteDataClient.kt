package com.starter.network

import javax.inject.Inject

class RemoteDataClient @Inject constructor(val dataService: RemoteDataService) {

    suspend fun getList() {
        dataService.getList()
    }
}