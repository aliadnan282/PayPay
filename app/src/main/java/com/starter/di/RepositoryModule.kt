package com.starter.di

import com.starter.network.RemoteDataClient
import com.starter.repository.AppRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    fun provideRepository(dataClient: RemoteDataClient): AppRepository{
        return AppRepository(dataClient)
    }
}