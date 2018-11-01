package com.bext

import android.util.Log

/**
 * Created by Birju Vachhani on 01/11/18.
 */

fun <T> Any.logD(obj: T) {
    Log.d(this::class.java.simpleName, obj.toString())
}

fun <T> Any.logE(obj: T) {
    Log.e(this::class.java.simpleName, obj.toString())
}

fun <T> Any.logI(obj: T) {
    Log.i(this::class.java.simpleName, obj.toString())
}

fun <T> Any.logV(obj: T) {
    Log.v(this::class.java.simpleName, obj.toString())
}

fun <T> Any.logW(obj: T) {
    Log.w(this::class.java.simpleName, obj.toString())
}

fun <T> Any.logWTF(obj: T) {
    Log.wtf(this::class.java.simpleName, obj.toString())
}

fun <T> Any.log(obj: T) {
    logV(obj)
}
