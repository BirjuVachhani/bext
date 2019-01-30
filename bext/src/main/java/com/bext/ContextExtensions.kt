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

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Birju Vachhani on 30/01/19.
 */

/**
 * gets system service and casts it to the requested type else throws [ClassCastException]
 * */
inline fun <reified T> Context.getService(serviceName: String): T {
    val service = getSystemService(serviceName)
    if (service is T) {
        return service
    } else {
        throw ClassCastException("Cannot cast service to the given type: ${T::class.java}")
    }
}

/**
 * Extension function to get Application class
 * */
inline fun <reified T : Application> Context.application(): T = with(this.applicationContext) {
    when (this) {
        is T -> return this
        else -> throw ClassCastException("Cannot cast ${T::class.java} class to Application class")
    }
}

/**
 * Extension function to check whether internet connectivity is available or not
 * @return true is internet connectivity is available, false otherwise
 * */
fun Context.hasInternet(): Boolean =
    this.getService<ConnectivityManager>(Context.CONNECTIVITY_SERVICE).activeNetworkInfo?.isConnected
        ?: false