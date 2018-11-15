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

import android.content.Context
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.widget.Toast

/**
 * Created by Birju Vachhani on 01/11/18.
 */

//------------------------------------------------------------------------
/**
 *  Toasts for Support Fragment
 */

// short toast from string
fun <T> Fragment.toast(obj: T) {
    Toast.makeText(this.requireContext(), obj.toString(), Toast.LENGTH_SHORT).show()
}

// short toast from string res
fun Fragment.toast(@StringRes messageId: Int) {
    Toast.makeText(this.requireContext(), requireContext().resources.getString(messageId), Toast.LENGTH_SHORT).show()
}

// long toast from string
fun <T> Fragment.longToast(obj: T) {
    Toast.makeText(this.requireContext(), obj.toString(), Toast.LENGTH_LONG).show()
}

// long toast from string res
fun Fragment.longToast(@StringRes messageId: Int) {
    Toast.makeText(this.requireContext(), requireContext().resources.getString(messageId), Toast.LENGTH_LONG).show()
}

//------------------------------------------------------------------------
/**
 *  Toasts for AppCompatActivity
 */

fun <T> Context.toast(obj: T) {
    Toast.makeText(this, obj.toString(), Toast.LENGTH_SHORT).show()
}

fun Context.toast(@StringRes messageId: Int) {
    Toast.makeText(this, this.getString(messageId), Toast.LENGTH_SHORT).show()
}

fun <T> Context.longToast(obj: T) {
    Toast.makeText(this, obj.toString(), Toast.LENGTH_LONG).show()
}

fun Context.longToast(@StringRes messageId: Int) {
    Toast.makeText(this, this.getString(messageId), Toast.LENGTH_LONG).show()
}

//------------------------------------------------------------------------
/**
 *  Toasts for Dialogs
 */

fun <T> AlertDialog.toast(obj: T) {
    Toast.makeText(this.context, obj.toString(), Toast.LENGTH_SHORT).show()
}

fun AlertDialog.toast(@StringRes messageId: Int) {
    Toast.makeText(this.context, this.context.getString(messageId), Toast.LENGTH_SHORT).show()
}

fun <T> AlertDialog.longToast(obj: T) {
    Toast.makeText(this.context, obj.toString(), Toast.LENGTH_LONG).show()
}

fun AlertDialog.longToast(@StringRes messageId: Int) {
    Toast.makeText(this.context, this.context.getString(messageId), Toast.LENGTH_SHORT).show()
}