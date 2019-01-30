package com.bext

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

/**
 * Created by Birju Vachhani on 30/01/19.
 */

/**
 * Extension function to hide soft keyboard for fragment
 * */
fun Fragment.hideKeyboard() = postHandler {
    requireContext().getService<InputMethodManager>(Context.INPUT_METHOD_SERVICE).hideSoftInputFromWindow(
        this.requireActivity().findViewById<View>(android.R.id.content)?.windowToken, 0
    )
}

/**
 * Extension function to get casted Application class
 * */
inline fun <reified T : Activity> Fragment.activity(): T = if (this is T)
    this as? T ?: throw ClassCastException("Unable to cast to Application class")
else
    throw ClassCastException("Unable to cast to Application class")