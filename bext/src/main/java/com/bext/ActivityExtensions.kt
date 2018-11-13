package com.bext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * Created by Birju Vachhani on 13/11/18.
 */

/* Hides Soft Input Keyboard */
fun Activity.hideKeyboard() {
    Handler().post {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(this.findViewById<View>(android.R.id.content).windowToken, 0)
    }
}

// Starts a new Activity
inline fun <reified T : Activity> Activity.navigateTo(func: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.func()
    this.startActivity(intent)
}

//Does a fragment transaction
fun AppCompatActivity.transact(func: FragmentTransaction.() -> Unit) {
    this.supportFragmentManager.beginTransaction().apply {
        func()
    }.commit()
}
