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

import android.util.Log

/*
 * Created by Birju Vachhani on 01 November 2018
 * Copyright © 2019 bext. All rights reserved.
 */

/**
 * Extension function to log data in DEBUG type
 * @obj is the obj of data which will be logged as string
 * Uses class name of obj as the log TAG
 * */
fun <T> Any.logD(obj: T) = Log.d(this::class.java.simpleName, obj.toString())

/**
 * Extension function to log data in ERROR type
 * @obj is the obj of data which will be logged as string
 * Uses class name of obj as the log TAG
 * */
fun <T> Any.logE(obj: T) = Log.e(this::class.java.simpleName, obj.toString())

/**
 * Extension function to log data in INFO type
 * @obj is the obj of data which will be logged as string
 * Uses class name of obj as the log TAG
 * */
fun <T> Any.logI(obj: T) {
    Log.i(this::class.java.simpleName, obj.toString())
}

/**
 * Extension function to log data in VERBOSE type
 * @obj is the obj of data which will be logged as string
 * Uses class name of obj as the log TAG
 * */
fun <T> Any.logV(obj: T) = Log.v(this::class.java.simpleName, obj.toString())

/**
 * Extension function to log data in WARNING type
 * @obj is the obj of data which will be logged as string
 * Uses class name of obj as the log TAG
 * */
fun <T> Any.logW(obj: T) = Log.w(this::class.java.simpleName, obj.toString())

/**
 * Extension function to log data in WTF type
 * @obj is the obj of data which will be logged as string
 * Uses class name of obj as the log TAG
 * */
fun <T> Any.logWTF(obj: T) = Log.wtf(this::class.java.simpleName, obj.toString())

/**
 * Simple Extension function to log general data in VERBOSE type
 * @obj is the obj of data which will be logged as string
 * Uses class name of obj as the log TAG
 * */
fun <T> Any.log(obj: T) = logV(obj)