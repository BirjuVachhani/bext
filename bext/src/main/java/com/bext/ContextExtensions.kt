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