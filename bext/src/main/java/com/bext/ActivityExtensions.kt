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

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * Created by Birju Vachhani on 13/11/18.
 */

/* Hides Soft Input Keyboard */
fun Activity.hideKeyboard() {
    Handler().post {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(this.findViewById<View>(android.R.id.content).windowToken, 0)
    }
}

// Starts a new Activity
inline fun <reified T : Activity> Activity.navigateTo(func: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.func()
    this.startActivity(intent)
}

//Does a fragment transaction
fun AppCompatActivity.transact(func: FragmentTransaction.() -> Unit) {
    this.supportFragmentManager.beginTransaction().apply {
        func()
    }.commit()
}
