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

package com.bext

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

/**
 * Created by Birju Vachhani on 30/01/19.
 */

/*
 * Created by Birju Vachhani on 30 January 2019
 * Copyright © 2019 bext. All rights reserved.
 */

/**
 * Extension function to hide soft keyboard for fragment
 * */
fun Fragment.hideKeyboard() = postHandler {
    requireContext().getService<InputMethodManager>(Context.INPUT_METHOD_SERVICE)?.hideSoftInputFromWindow(
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