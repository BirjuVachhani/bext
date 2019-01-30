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

package com.bext

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

/**
 * Created by Birju Vachhani on 01/11/18.
 */

/**
 * Dsl Marker for [AlertDialogBuilder] extension helper
 * */
@DslMarker
annotation class AlertDialogExtensions

/**
 *  Extension to create and display an [AlertDialog] and shows it.
 *  @param dialogBuilder is a Helper class to process dialog DSL and create
 *  a [AlertDialog.Builder] instance.
 *
 *  It uses safe way to show dialog which means dialog will only be shown if the activity is not finishing.
 * */
fun Activity.alertDialog(dialogBuilder: AlertDialogBuilder.() -> Unit): AlertDialog =
    AlertDialogBuilder().apply(dialogBuilder).createDialog(this).apply {
        takeUnless { isFinishing }?.show()
    }

/**
 *  Extension to create and display an [AlertDialog] and shows it.
 *  @param dialogBuilder is a Helper class to process dialog DSL and
 *  create a [AlertDialog.Builder] instance.
 *
 *  It uses safe way to show dialog which means dialog will only be shown if the activity is not finishing.
 * */
fun Fragment.alertDialog(dialogBuilder: AlertDialogBuilder.() -> Unit): AlertDialog =
    AlertDialogBuilder().apply(dialogBuilder).createDialog(requireContext()).apply {
        takeUnless { activity == null && requireActivity().isFinishing }?.show()
    }


/**
 * Extension to create an [AlertDialog]
 * @param dialogBuilder is a Helper class to process dialog DSL and
 * create a [AlertDialog.Builder] instance.
 * @return [AlertDialog] object which can be used to show the dialog
 * */
fun Activity.createDialog(dialogBuilder: AlertDialogBuilder.() -> Unit): AlertDialog =
    AlertDialogBuilder().apply(dialogBuilder).createDialog(this)

/**
 * Extension to create an [AlertDialog]
 * @param dialogBuilder is a Helper class to process dialog DSL and
 * create a [AlertDialog.Builder] instance.
 * @return [AlertDialog] object which can be used to show the dialog
 * */
fun Fragment.createDialog(dialogBuilder: AlertDialogBuilder.() -> Unit): AlertDialog =
    AlertDialogBuilder().apply(dialogBuilder).createDialog(this.requireContext())

/**
 * [AlertDialog] Extension Helper class
 * */
@AlertDialogExtensions
class AlertDialogBuilder internal constructor() {

    var title: Any = R.string.app_name
    var message: Any = R.string.dialog_message_default
    private var positiveButton = DialogButton(R.string.positive_button_default_text)
    private var negativeButton: DialogButton? = null
    var cancelable: Boolean = true
    internal var dismissFunc: (DialogInterface) -> Unit = {}
    internal var configFunc: (AlertDialog.Builder) -> Unit = {}

    /**
     * Adds positive button to the alert dialog.
     * @param func is a lambda with [DialogButton] class receiver
     * */
    fun positiveButton(func: DialogButton.() -> Unit) {
        positiveButton = DialogButton().apply(func)
    }

    /**
     * Adds negative button to the alert dialog.
     * This is optional. If you want to display a dialog with
     * only one button then don't use it.
     * @param func is a lambda with [DialogButton] class receiver
     * */
    fun negativeButton(func: DialogButton.() -> Unit) {
        negativeButton = DialogButton().apply(func)
    }

    /**
     * Adds positive button to the alert dialog.
     * @return [AlertDialog] instance which is used to show alert
     * */
    internal fun createDialog(context: Context): AlertDialog = AlertDialog.Builder(context).apply {
        setTitle(context.parseToString(title))
        setMessage(context.parseToString(message))
        setPositiveButton(context.parseToString(positiveButton.text)) { dialog, _ ->
            positiveButton.clickFunc(dialog)
        }
        negativeButton?.let { button ->
            setNegativeButton(context.parseToString(button.text)) { dialog, _ ->
                button.clickFunc(dialog)
            }
        }
        setCancelable(cancelable)
        setOnDismissListener { dialog ->
            dismissFunc(dialog)
        }
        configFunc(this)
    }.create()

    private fun Context.parseToString(obj: Any): String = when (obj) {
        is CharSequence -> obj.toString()
        is Int -> this.getString(obj)
        else -> throw ClassCastException("Unable to cast $obj to String or Int")
    }

    /**
     * Provides a block to be executed when dialog is dismissed
     * */
    fun onDismiss(func: (DialogInterface) -> Unit) {
        dismissFunc = func
    }

    /**
     * Provides a config block to be executed when dialog is created
     * */
    fun config(func: AlertDialog.Builder.() -> Unit) {
        configFunc = func
    }

    @AlertDialogExtensions
    class DialogButton internal constructor() {
        var text: Any = R.string.negative_button_default_text
        internal var clickFunc: (DialogInterface) -> Unit = {}

        internal constructor(text: Any) : this() {
            this.text = text
        }

        /**
         * Provides a function block to perform actions on
         * positive button click
         * @param func is the function which will be
         * called on the positive button click
         * */
        fun onClick(func: (DialogInterface) -> Unit) {
            this.clickFunc = func
        }
    }
}
