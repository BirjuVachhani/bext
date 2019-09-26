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

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import androidx.annotation.RequiresApi

/*
 * Created by Birju Vachhani on 30 January 2019
 * Copyright © 2019 bext. All rights reserved.
 */

/**
 * gets system service and casts it to the requested type else throws [ClassCastException]
 * */
inline fun <reified T> Context.getService(serviceName: String): T? =
    getSystemService(serviceName).let { service ->
        return when (service) {
            is T -> service
            else -> null
        }
    }

@RequiresApi(23)
@Throws(NullPointerException::class)
inline fun <reified T> Context.getService(): T = getSystemService(T::class.java)
    ?: throw NullPointerException("Cannot find requested service: ${T::class.java.simpleName}")

@RequiresApi(23)
inline fun <reified T> Context.getServiceOrNull(): T? = getSystemService(T::class.java)

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
    this.getService<ConnectivityManager>(Context.CONNECTIVITY_SERVICE)
        ?.activeNetworkInfo?.isConnected
        ?: false

/**
 * Property Extension to get [LayoutInflater] from [Context] directly
 * */
val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)

/**
 * Retrieves current quality of service which is the type of internet connection used by the device
 */
@RequiresApi(21)
fun Context.getQualityOfService(): String {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            ?: return "not connected"
    val network =
        connectivityManager.allNetworks.firstOrNull() ?: return "not connected"
    val capabilities = connectivityManager.getNetworkCapabilities(network)
    return if (capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true) {
        "WIFI"
    } else {
        getInternetType()
    }
}

/**
 * Retrieves which type of internet connection is being used when this method called. [e.g. 2G, 3G, 4G, 5G]
 * @return String The type of internet being used
 */
private fun Context.getInternetType(): String {
    val mTelephonyManager =
        getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager
            ?: return "not connected"
    return when (mTelephonyManager.networkType) {
        TelephonyManager.NETWORK_TYPE_GPRS,
        TelephonyManager.NETWORK_TYPE_EDGE,
        TelephonyManager.NETWORK_TYPE_CDMA,
        TelephonyManager.NETWORK_TYPE_1xRTT,
        TelephonyManager.NETWORK_TYPE_IDEN -> "2G"
        TelephonyManager.NETWORK_TYPE_UMTS,
        TelephonyManager.NETWORK_TYPE_EVDO_0,
        TelephonyManager.NETWORK_TYPE_EVDO_A,
        TelephonyManager.NETWORK_TYPE_HSDPA,
        TelephonyManager.NETWORK_TYPE_HSUPA,
        TelephonyManager.NETWORK_TYPE_HSPA,
        TelephonyManager.NETWORK_TYPE_EVDO_B,
        TelephonyManager.NETWORK_TYPE_EHRPD,
        TelephonyManager.NETWORK_TYPE_HSPAP -> "3G"
        TelephonyManager.NETWORK_TYPE_LTE -> "4G"
        TelephonyManager.NETWORK_TYPE_NR -> "5G"
        else -> "not connected"
    }
}