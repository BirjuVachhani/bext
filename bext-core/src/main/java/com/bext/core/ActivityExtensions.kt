/*
 * Copyright 2020 BirjuVachhani (https://github.com/BirjuVachhani)
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

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

/*
 * Created by Birju Vachhani on 13 November 2018
 * Copyright © 2019 bext. All rights reserved.
 */

/**
 * Extension function to hide soft keyboard for activity
 * */
fun Activity.hideKeyboard() = postHandler {
    getService<InputMethodManager>(Context.INPUT_METHOD_SERVICE)?.hideSoftInputFromWindow(
        this.findViewById<View>(android.R.id.content).windowToken, 0
    )
}

/**
 * Extension function to start/launch new [Activity]
 * @param func is a lambda function with [Intent]'s receiver which provides a lambda block
 * to perform action on to the [Intent] which will be used to start Activity
 * T takes name of the destination [Activity]
 * */
inline fun <reified T : Activity> Activity.navigateTo(func: Intent.() -> Unit = {}) =
    this.startActivity(Intent(this, T::class.java).apply(func))

/**
 * Extension function to start/launch new [Activity] without passing any data
 * */
inline fun <reified T : Activity> Activity.navigateTo() =
    this.startActivity(Intent(this, T::class.java))

/**
 * Extension function to perform [Fragment] transaction in [Activity]
 * @param func is a lambda function with [FragmentTransaction]'s receiver
 * which provides access to the transaction instance which will be
 * used to process [FragmentTransaction]
 * */
fun AppCompatActivity.transact(func: FragmentTransaction.() -> Unit) =
    this.supportFragmentManager.beginTransaction().apply(func).commit()

/**
 * Extension function to start/launch new [Activity] for result without passing any data
 * */
inline fun <reified T : Activity> Activity.navigateForResultTo(requestCode: Int) =
    this.startActivityForResult(Intent(this, T::class.java), requestCode)

/**
 * Extension function to start/launch new [Activity] for result
 * @param func is a lambda function with [Intent]'s receiver which provides a lambda block
 * to perform action on to the [Intent] which will be used to start Activity
 * */
inline fun <reified T : Activity> Activity.navigateForResultTo(
    requestCode: Int,
    func: Intent.() -> Unit = {}
) =
    this.startActivityForResult(Intent(this, T::class.java).apply(func), requestCode)