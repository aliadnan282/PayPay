package com.starter.model

import com.google.gson.annotations.SerializedName

data class CurrencyTypes(
    @SerializedName("currencies") val currencies: Map<String, String>
)