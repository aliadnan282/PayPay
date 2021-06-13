package com.paypay.model

import com.google.gson.annotations.SerializedName

data class CurrencyRates(
    @SerializedName("timestamp")var timestamp: Long = 0,
    @SerializedName("source") val source: String,
    @SerializedName("quotes") val quotes: Map<String, Double>

)