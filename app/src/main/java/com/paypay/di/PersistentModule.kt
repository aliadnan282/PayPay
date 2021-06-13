package com.paypay.di

import android.app.Application
import androidx.room.Room
import com.paypay.database.AppDatabase
import com.paypay.database.dao.CurrencyDao
import com.paypay.helper.AppConstants
import com.paypay.preferences.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistentModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase{
        return  Room
            .databaseBuilder(application, AppDatabase::class.java,AppConstants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    }

    @Provides
    @Singleton
    fun provideCurrencyDao(appDatabase: AppDatabase): CurrencyDao{
        return appDatabase.getCurrencyDao()
    }

    @Provides
    @Singleton
    fun provideSharePreferences(application: Application): AppPreferences{
        return AppPreferences(application)
    }
}