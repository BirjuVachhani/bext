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

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

/*
 * Created by Birju Vachhani on 01 November 2018
 * Copyright © 2019 bext. All rights reserved.
 */

/**
 *  Toasts for Support Fragment
 */

/**
 * [Fragment] Extension function to display a toast for short time
 * @param obj is the object which will be displayed as a string in the toast
 * */
fun <T> Fragment.toast(obj: T) =
    Toast.makeText(this.requireContext(), obj.toString(), Toast.LENGTH_SHORT).show()

/**
 * [Fragment] Extension function to display a toast for short time
 * @param messageId is the string resource which will be displayed as the toast message
 * */
fun Fragment.toast(@StringRes messageId: Int) =
    Toast.makeText(
        this.requireContext(),
        requireContext().resources.getString(messageId), Toast.LENGTH_SHORT
    ).show()

/**
 * [Fragment] Extension function to display a toast for long time
 * @param obj is the object which will be displayed as a string in the toast
 * */
fun <T> Fragment.longToast(obj: T) =
    Toast.makeText(this.requireContext(), obj.toString(), Toast.LENGTH_LONG).show()

/**
 * [Fragment] Extension function to display a toast for long time
 * @param messageId is the string resource which will be displayed as the toast message
 * */
fun Fragment.longToast(@StringRes messageId: Int) =
    Toast.makeText(
        this.requireContext(),
        requireContext().resources.getString(messageId), Toast.LENGTH_LONG
    ).show()

/**
 *  Toasts for AppCompatActivity
 */

/**
 * [Context] Extension function to display a toast for short time
 * @param obj is the object which will be displayed as a string in the toast
 * */
fun <T> Context.toast(obj: T) =
    Toast.makeText(this, obj.toString(), Toast.LENGTH_SHORT).show()

/**
 * [Context] Extension function to display a toast for short time
 * @param messageId is the string resource which will be displayed as the toast message
 * */
fun Context.toast(@StringRes messageId: Int) =
    Toast.makeText(this, this.getString(messageId), Toast.LENGTH_SHORT).show()

/**
 * [Context] Extension function to display a toast for long time
 * @param obj is the object which will be displayed as a string in the toast
 * */
fun <T> Context.longToast(obj: T) =
    Toast.makeText(this, obj.toString(), Toast.LENGTH_LONG).show()

/**
 * [Context] Extension function to display a toast for long time
 * @param messageId is the string resource which will be
 * displayed as the toast message
 * */
fun Context.longToast(@StringRes messageId: Int) =
    Toast.makeText(this, this.getString(messageId), Toast.LENGTH_LONG).show()

/**
 *  Toasts for Dialogs
 */

/**
 * [AlertDialog] Extension function to display a toast for short time
 * @param obj is the object which will be displayed as a string in the toast
 * */
fun <T> AlertDialog.toast(obj: T) =
    Toast.makeText(this.context, obj.toString(), Toast.LENGTH_SHORT).show()

/**
 * [AlertDialog] Extension function to display a toast for short time
 * @param messageId is the string resource which will be displayed as the toast message
 * */
fun AlertDialog.toast(@StringRes messageId: Int) =
    Toast.makeText(
        this.context,
        this.context.getString(messageId), Toast.LENGTH_SHORT
    ).show()

/**
 * [AlertDialog] Extension function to display a toast for long time
 * @param obj is the object which will be displayed as a string in the toast
 * */
fun <T> AlertDialog.longToast(obj: T) =
    Toast.makeText(this.context, obj.toString(), Toast.LENGTH_LONG).show()

/**
 * [AlertDialog] Extension function to display a toast for long time
 * @param messageId is the string resource which will be displayed as the toast message
 * */
fun AlertDialog.longToast(@StringRes messageId: Int) =
    Toast.makeText(
        this.context,
        this.context.getString(messageId), Toast.LENGTH_SHORT
    ).show()