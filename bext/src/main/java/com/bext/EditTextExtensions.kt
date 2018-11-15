/*
 * Copyright 2018 BirjuVachhani
 * </p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * </p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * </p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bext

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by Birju Vachhani on 01-11-2018.
 */

fun EditText.textString(): String {
    return this.text.toString()
}

/*  */
fun EditText.toInt(default: Int = 0): Int {
    return this.textString().toIntOrNull() ?: default
}

fun EditText.toFloat(default: Float = 0.0f): Float {
    return this.textString().toFloatOrNull() ?: default
}

fun EditText.toDouble(default: Double = 0.0): Double {
    return this.textString().toDoubleOrNull() ?: default
}

fun EditText.toLong(default: Long = 0): Long {
    return this.textString().toLongOrNull() ?: default
}

/* Extension for TextChangedListener */
fun EditText.textWatcher(textChangedBuilderFunc: TextWatcherBuilder.() -> Unit) {
    val textWatcherBuilder = TextWatcherBuilder().apply {
        textChangedBuilderFunc()
    }
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(e: Editable?) {
            textWatcherBuilder.after(e)
        }

        override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
            textWatcherBuilder.before(text, start, count, after)
        }

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            textWatcherBuilder.onChanged(text, start, before, count)
        }
    })
}

/* Extension for EditorActionListener */
fun EditText.imeActionListener(action: Int, consume: Boolean = false, func: (text: String) -> Unit) {
    this.setOnEditorActionListener { v, actionId, event ->
        if (actionId == action) {
            func(this.textString())
        }
        return@setOnEditorActionListener consume
    }
}

/* check if the text is a valid email address or not */
fun EditText.isValidEmail(): Boolean {
    if (TextUtils.isEmpty(this.textString())) return false
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this.textString()).matches()
}

/* Used for building TextWatcher */
class TextWatcherBuilder {
    var after: (editable: Editable?) -> Unit = { _ -> }
    var before: (text: CharSequence?, start: Int, count: Int, after: Int) -> Unit = { _, _, _, _ -> }
    var onChanged: (text: CharSequence?, start: Int, before: Int, count: Int) -> Unit = { _, _, _, _ -> }
}