package com.paypay.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paypay.database.dao.CurrencyDao
import com.paypay.database.entity.Currency

@Database (entities = [Currency::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao
}