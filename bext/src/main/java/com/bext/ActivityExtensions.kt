package com.bext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

/**
 * Created by Birju Vachhani on 13/11/18.
 */

/**
 * Extension function to hide soft keyboard for activity
 * */
fun Activity.hideKeyboard() = postHandler {
    getService<InputMethodManager>(Context.INPUT_METHOD_SERVICE).hideSoftInputFromWindow(
        this.findViewById<View>(android.R.id.content).windowToken, 0
    )
}

/**
 * Extension function to start/launch new [Activity]
 * @param func is a lambda function with [Intent]'s receiver which provides a lambda block
 * to perform action on to the [Intent] which will be used to start Activity
 * T takes name of the destination [Activity]
 * */
inline fun <reified T : Activity> Activity.navigateTo(func: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.func()
    this.startActivity(intent)
}

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
    this.supportFragmentManager.beginTransaction().apply {
        func()
    }.commit()