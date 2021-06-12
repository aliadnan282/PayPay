package com.starter.persistent

import androidx.room.Room
import com.starter.database.AppDatabase
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import org.junit.After

@RunWith(RobolectricTestRunner::class)
abstract class LocalDatabase {
    lateinit var db: AppDatabase

    @Before
    fun initDB(){
        db = Room.inMemoryDatabaseBuilder(getApplicationContext(), AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDB(){
        db.close()
    }
}