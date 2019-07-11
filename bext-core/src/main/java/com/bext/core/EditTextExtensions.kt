/*
 * Copyright 2019 BirjuVachhani (https://github.com/BirjuVachhani)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bext.core

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/*
 * Created by Birju Vachhani on 01 November 2018
 * Copyright © 2019 bext. All rights reserved.
 */

/**
 * Dsl Marker for [TextWatcherBuilder] extension helper
 * */
@DslMarker
annotation class TextWatcherMarker

/**
 * Gets the current text available in the [EditText]
 * @return most recent text available in the [EditText] widget
 * */
fun EditText.textString(): String = this.text.toString()

/**
 * Retrieves data as Int from [EditText] widget
 * @return [EditText] content as Int
 * */
fun EditText.toInt(default: Int = 0): Int = this.textString().toIntOrNull() ?: default

/**
 * Retrieves data as [Float] from [EditText] widget
 * @return [EditText] content as [Float]
 * */
fun EditText.toFloat(default: Float = 0.0f): Float =
    this.textString().toFloatOrNull() ?: default

/**
 * Retrieves data as [Double] from [EditText] widget
 * @return [EditText] content as [Double]
 * */
fun EditText.toDouble(default: Double = 0.0): Double =
    this.textString().toDoubleOrNull() ?: default

/**
 * Retrieves data as [Long] from [EditText] widget
 * @return [EditText] content as [Long]
 * */
fun EditText.toLong(default: Long = 0): Long =
    this.textString().toLongOrNull() ?: default

/**
 * Extension for TextChangedListener to listen text changes in [EditText].
 * Processes provided DSL and creates TextChangedListener on [EditText]
 * @param textChangedBuilderFunc is the helper
 * class [TextWatcherBuilder]'s lambda with receiver
 * */
fun EditText.textWatcher(textChangedBuilderFunc: TextWatcherBuilder.() -> Unit) {
    val textWatcherBuilder = TextWatcherBuilder().apply {
        textChangedBuilderFunc()
    }
    this.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(e: Editable?) =
            textWatcherBuilder.afterFunc(e)

        override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) =
            textWatcherBuilder.beforeFunc(text, start, count, after)

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) =
            textWatcherBuilder.onChangedFunc(text, start, before, count)
    })
}

/**
 * Extension function to set OnEditorActionListener on [EditText]
 * @param action is EditorInfo.IME_ACTION which specifies on
 * which action it should listen
 *
 * @param consume determines whether the action is consumed or
 * not and passes accordingly to the super
 *
 * @param func is a function that will be executed when
 * the specified action is performed on the keyboard
 * */
fun EditText.imeActionListener(
    action: Int, consume: Boolean = false,
    func: (text: String) -> Unit
) = this.setOnEditorActionListener { _, actionId, _ ->
    if (actionId == action) {
        func(this.textString())
    }
    return@setOnEditorActionListener consume
}

/**
 * verifies whether the entered email ID is valid or not
 * @return true if the email matches with the regex, false otherwise.
 * */
fun EditText.isValidEmail(): Boolean =
    if (this.textString().isEmpty()) false
    else this.textString().isNotEmpty()

            && android.util.Patterns.EMAIL_ADDRESS.matcher(this.textString()).matches()

/**
 * Helper class to process textWatcher DSL and create required functions from it
 * @property afterFunc stores a function which should be called after
 * the text change in [EditText]
 *
 * @property beforeFunc stores a function which should be called before
 * the text change in [EditText]
 *
 * @property onChangedFunc stores a function which should be called when
 * the text changes in [EditText]
 *
 * */
@TextWatcherMarker
class TextWatcherBuilder {
    internal var afterFunc: (editable: Editable?) -> Unit = { _ -> }
    internal var beforeFunc: (
        text: CharSequence?,
        start: Int, count: Int, after: Int
    ) -> Unit = { _, _, _, _ -> }
    internal var onChangedFunc: (
        text: CharSequence?,
        start: Int, before: Int, count: Int
    ) -> Unit = { _, _, _, _ -> }

    /**
     * Provides a lambda block which will be executed after text changes on [EditText]
     * */
    fun after(func: (editable: Editable?) -> Unit) {
        this.afterFunc = func
    }

    /**
     * Provides a lambda block which will be executed before text changes on [EditText]
     * */
    fun before(
        func: (
            text: CharSequence?, start: Int,
            count: Int, after: Int
        ) -> Unit
    ) {
        this.beforeFunc = func
    }

    /**
     * Provides a lambda block which will be executed on text changes on [EditText]
     * */
    fun onChange(
        func: (
            text: CharSequence?, start: Int,
            before: Int, count: Int
        ) -> Unit
    ) {
        this.onChangedFunc = func
    }
}

/**
 * Extension function to receive text change events on [EditText]
 * @param func is a function that should be executed
 * whenever the text in [EditText] is changed
 *
 * @param newText provides the final changed text in the [EditText]
 * */
fun EditText.onTextChange(func: (newText: String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(e: Editable?) {}

        override fun beforeTextChanged(
            text: CharSequence?,
            start: Int, count: Int, after: Int
        ) {
            //do nothing
        }

        override fun onTextChanged(
            text: CharSequence?,
            start: Int, before: Int, count: Int
        ) = func.invoke(text.toString())
    })
}