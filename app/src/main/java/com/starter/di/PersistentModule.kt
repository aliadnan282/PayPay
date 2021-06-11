package com.starter.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.starter.database.AppDatabase
import com.starter.database.dao.CurrencyDao
import com.starter.helper.AppConstants
import com.starter.preferences.AppPreferences
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