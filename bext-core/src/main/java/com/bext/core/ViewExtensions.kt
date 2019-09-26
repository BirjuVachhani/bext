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

import android.view.LayoutInflater
import android.view.View

/*
 * Created by Birju Vachhani on 03 November 2018
 * Copyright © 2019 bext. All rights reserved.
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

/**
 * Property Extension to get [LayoutInflater] from [View] directly
 * */
val View.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this.context)

/**
 * Enables a view by settings [View.isEnabled] property to true
 */
fun View.enable() {
    isEnabled = true
}

/**
 * Disables a view by settings [View.isEnabled] property to false
 */
fun View.disable() {
    isEnabled = false
}