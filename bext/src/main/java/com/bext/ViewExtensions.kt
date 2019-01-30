package com.bext

import android.view.View


/**
 * Created by Birju Vachhani on 03-11-2018.
 */

/**
 * Extension function to set a [View]'s visibility to View.VISIBLE
 * */
fun View.show() {
    this.visibility = View.VISIBLE
}

/**
 * Extension function to set a [View]'s visibility to View.GONE
 * */
fun View.hide() {
    this.visibility = View.GONE
}

/**
 * Extension function to set a [View]'s visibility to View.INVISIBLE
 * */
fun View.invisible() {
    this.visibility = View.INVISIBLE
}

/**
 * Extension function to check if the [View] is visible
 * */
fun View.isVisible(): Boolean = this.visibility == View.VISIBLE

/**
 * Extension function to check if the [View] is hidden
 * */
fun View.isHidden(): Boolean = this.visibility == View.GONE

/**
 * Extension function to check if the [View] is invisible
 * */
fun View.isInvisible(): Boolean = this.visibility == View.INVISIBLE
