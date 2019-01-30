package com.bext

import android.os.Handler

/**
 * Created by Birju Vachhani on 16-12-2018.
 */

/**
 * Extension function for posting a [Handler]
 * */
fun postHandler(func: () -> Unit) =
    Handler().post { func() }

/**
 * Extension function for posting a delayed [Handler]
 * */
fun postHandler(delay: Long, func: () -> Unit) =
    Handler().postDelayed(func, delay)