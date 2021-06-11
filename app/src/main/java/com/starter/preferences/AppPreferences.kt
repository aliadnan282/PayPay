package com.starter.preferences

import android.app.Application
import android.content.Context
import com.starter.helper.AppConstants
import javax.inject.Inject

class AppPreferences @Inject constructor(application: Application) {
    private val preference = application.getSharedPreferences(
        AppConstants.SHARED_PREFERENCE_NAME,
        Context.MODE_PRIVATE
    )

    fun getString(key: String): String? {
        return preference.getString(key, "")
    }

    fun getLong(key: String): Long {
        return preference.getLong(key, -1)
    }

    fun setString(key: String, value: String) {
        preference.edit().putString(key, value).apply()
    }

    fun setLong(key: String, value: Long) {
        preference.edit().putLong(key, value).apply()
    }
}