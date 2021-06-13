package com.paypay.helper

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object AppUtils{
    inline fun <reified T> fromJson(context: Context, fileName: Int): T {
        val jsonString = context.resources?.openRawResource(fileName)?.bufferedReader()
            .use { it?.readText() }
        return Gson().fromJson(jsonString, object : TypeToken<T>() {}.type)
    }

    inline fun <reified T> toJson(type: T): String {
        return Gson().toJson(type)
    }
}