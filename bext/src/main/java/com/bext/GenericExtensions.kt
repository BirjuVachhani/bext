package com.bext

/**
 * Created by Birju Vachhani on 30/01/19.
 */

/**
 * Extension function to retrieve name string of any class
 * */
inline fun <reified T : Any> T.className(): String = this::class.java.simpleName