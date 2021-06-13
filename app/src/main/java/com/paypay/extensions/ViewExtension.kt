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

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            if (!text.toString().isNullOrEmpty())
            afterTextChanged.invoke(editable.toString())
        }
    })
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