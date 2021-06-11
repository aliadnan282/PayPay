package com.starter.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.starter.database.entity.Currency

@Dao
interface CurrencyDao {
    @Query("SELECT * from currency")
    suspend fun getAllList(): List<Currency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAllRate(rateList: List<Currency>)
}