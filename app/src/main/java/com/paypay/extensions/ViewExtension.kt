package com.paypay.extensions

import android.content.res.Resources
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.paypay.BuildConfig
import com.paypay.R
import java.util.*

fun View.gone() {
    visibility = View.GONE
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun EditText.afterTextChange(call: (String?)-> Unit )= object :TextWatcher{
    override fun afterTextChanged(text: Editable?) {
        if (!text.toString().isNullOrEmpty())
        call.invoke(text.toString())
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }
}

fun Resources.getFlagResource(flagName: String): Int {
    val id = getIdentifier(
        "_${flagName.lowercase(Locale.ROOT)}",
        "drawable",
        BuildConfig.APPLICATION_ID
    )
    if (id == 0) {
        return  R.drawable._no_flag
    }
    return id
}