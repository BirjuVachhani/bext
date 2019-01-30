/*
 * Copyright 2019 BirjuVachhani
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.bext

import android.annotation.SuppressLint
import android.content.SharedPreferences

/**
 * Created by Birju Vachhani on 16-12-2018.
 */

/**
 * Extension function for [SharedPreferences] to modify it in asynchronously
 * @param key is the key for [SharedPreferences]
 * @param value is the data which will be stored into [SharedPreferences]
 * */
fun SharedPreferences.write(key: String, value: Any) =
    this.edit()?.apply {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            is Boolean -> putBoolean(key, value)
        }
        apply()
    }

/**
 * Extension function for [SharedPreferences] to modify it synchronously
 * @param key is the key for [SharedPreferences]
 * @param value is the data which will be stored into [SharedPreferences]
 * */
@SuppressLint("ApplySharedPref")
fun SharedPreferences.writeSync(key: String, value: Any) =
    this.edit()?.apply {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            is Boolean -> putBoolean(key, value)
        }
        commit()
    }

/**
 * Extension for set operator for [SharedPreferences] which will be
 * used to save data into [SharedPreferences]
 * */
operator fun SharedPreferences.set(key: String, value: Any) =
    this.edit()?.apply {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            is Boolean -> putBoolean(key, value)
        }
        apply()
    }

/**
 * Extension to clear [SharedPreferences]
 * */
fun SharedPreferences.clear() = this.edit().clear().apply()

/**
 * Extension function to remove a key-value pair from [SharedPreferences]
 * */
fun SharedPreferences.remove(key: String) = this.edit().remove(key).apply()