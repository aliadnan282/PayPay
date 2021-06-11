package com.starter.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

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