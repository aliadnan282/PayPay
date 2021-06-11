package com.starter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.starter.database.dao.CurrencyDao
import com.starter.database.entity.Currency

@Database (entities = [Currency::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao
}