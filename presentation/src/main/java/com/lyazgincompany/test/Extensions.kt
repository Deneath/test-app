package com.lyazgincompany.test

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.DecimalFormat

fun EditText.addSimpleTextChangeListener(textChangedCallback: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) =
            textChangedCallback(p0.toString())

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)  = Unit
    })
}

fun Double.formatToString(): String {
    val decimalFormat = DecimalFormat("##.##")
    return decimalFormat.format(this)
}

fun String.formatToDouble(): Double {
    val decimalFormat = DecimalFormat("##.##")
    return decimalFormat.parse(this)?.toDouble() ?: 0.0
}