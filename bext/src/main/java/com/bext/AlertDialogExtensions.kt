/*
 * Copyright 2018 BirjuVachhani
 * </p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * </p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * </p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bext

import android.app.Activity
import android.content.Context
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog

/**
 * Created by Birju Vachhani on 01/11/18.
 */


/**
 * Dsl Marker for AlertDialogBuilder extension helper
 * */
@DslMarker
annotation class AlertDialogExtensions

/**
 *  Extension to create and display an AlertDialog and shows it.
 *
 *  @param dialogBuilder is a Helper class to process dialog DSL and create a AlertDialog.Builder instance.
 *
 * */
fun Activity.alertDialog(dialogBuilder: AlertDialogBuilder.() -> Unit) {

    AlertDialogBuilder(this).apply {
        dialogBuilder()
    }.createDialog().show()
}

/**
 *  Extension to create and display an AlertDialog and shows it.
 *
 *  @param dialogBuilder is a Helper class to process dialog DSL and create a AlertDialog.Builder instance.
 *
 * */
fun Fragment.alertDialog(dialogBuilder: AlertDialogBuilder.() -> Unit) {
    if (this.context != null) {
        AlertDialogBuilder(requireContext()).apply {
            dialogBuilder()
        }.createDialog().show()
    }
}

/**
 * Extension to create an AlertDialog
 *
 * @param dialogBuilder is a Helper class to process dialog DSL and create a AlertDialog.Builder instance.
 *
 * @return AlertDialog object which can be used to show the dialog
 * */
fun Activity.createDialog(dialogBuilder: AlertDialogBuilder.() -> Unit): AlertDialog {
    return AlertDialogBuilder(this).apply {
        dialogBuilder()
    }.createDialog()
}

/**
 * AlertDialog Extension Helper class
 *
 * */
@AlertDialogExtensions
class AlertDialogBuilder internal constructor(private val context: Context) {

    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)

    var title: String = context.getString(R.string.app_name)
    var message: String = context.getString(R.string.dialog_message_default)
    private var positiveButtonText = context.getString(R.string.positive_button_default_text)
    private var negativeButtonText = context.getString(R.string.negative_button_default_text)
    private var positiveButtonClick: () -> Unit = {}
    private var negativeButtonClick: () -> Unit = {}

    /**
     * Adds positive button to the alert dialog.
     *
     * @param func is a lamda with PositiveButton class receiver
     * */
    fun positiveButton(func: PositiveButton.() -> Unit) {
        val positiveButton = PositiveButton(context)
        positiveButton.func()
        positiveButtonText = positiveButton.text
        positiveButtonClick = positiveButton.clickEvent
        builder.setPositiveButton(positiveButtonText) { dialog, _ ->
            positiveButtonClick.invoke()
            dialog.dismiss()
        }
    }

    /**
     * Adds negative button to the alert dialog.
     *
     * This is optional. If you want to display a dialog with only one button then don't use it.
     *
     * @param func is a lamda with PositiveButton class receiver
     * */
    fun negativeButton(func: NegativeButton.() -> Unit) {
        val negativeButton = NegativeButton(context)
        negativeButton.func()
        negativeButtonText = negativeButton.text
        negativeButtonClick = negativeButton.clickEvent
        builder.setNegativeButton(negativeButtonText) { dialog, _ ->
            negativeButtonClick.invoke()
            dialog.dismiss()
        }
    }

    /**
     * Adds positive button to the alert dialog.
     *
     * @return AlertDialog instance which is used to show alert
     * */
    internal fun createDialog(): AlertDialog {
        builder.setMessage(message)
        builder.setTitle(title)
        builder.setPositiveButton(positiveButtonText) { dialog, _ ->
            positiveButtonClick()
            dialog.dismiss()
        }
        return builder.create()
    }


    /**
     * PositiveButton Helper class to process positive button DSL and add a positive button in the dialog.
     *
     * @property textId gains the priority when both text and textId is used
     * @property onClick is a small DSL function to add click event on positive button
     *
     * */
    @AlertDialogExtensions
    class PositiveButton internal constructor(private val context: Context) {
        var text: String = context.getString(R.string.negative_button_default_text)
        internal var clickEvent: () -> Unit = {}

        /**
         * Used to process string resources
         *
         * @param id is a string resource id which is resolved to a string
         *
         * returns resolved string from resource
         * */
        fun from(@StringRes id: Int): String {
            return context.getString(id)
        }

        /**
         * Provides a function block to perform actions on positive button click
         *
         * @param func is the function which will be called on the positive button click
         *
         * */
        fun onClick(func: () -> Unit) {
            clickEvent = func
        }
    }

    /**
     * NegativeButton Helper class to process negative button DSL and add a negative button in the dialog.
     *
     * @property textId gains the priority when both text and textId is used
     * @property onClick is a small DSL function to add click event on positive button
     *
     * */
    @AlertDialogExtensions
    class NegativeButton internal constructor(private val context: Context) {
        var text: String = context.getString(R.string.positive_button_default_text)
        internal var clickEvent: () -> Unit = {}

        /**
         * Used to process string resources
         *
         * @param id is a string resource id which is resolved to a string
         *
         * returns resolved string from resource
         * */
        fun from(@StringRes id: Int): String {
            return context.getString(id)
        }

        /**
         * Provides a function block to perform actions on positive button click
         *
         * @param func is the function which will be called on the positive button click
         *
         * */
        fun onClick(func: () -> Unit) {
            clickEvent = func
        }
    }
}

