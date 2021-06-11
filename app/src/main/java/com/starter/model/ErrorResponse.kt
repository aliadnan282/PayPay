package com.starter.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    @SerializedName("http_response")
    val httpResponse: Int? = 0,

    @SerializedName("code")
    val code: String? = "",

    @SerializedName("success")
    val success: Boolean = false,

    @SerializedName("cmd")
    val cmd: String? = "",

    @SerializedName("message")
    var message: String? = "",

    @SerializedName("title")
    var title: String? = ""
)