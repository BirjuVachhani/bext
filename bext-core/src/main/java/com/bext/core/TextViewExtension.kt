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

package com.bext.core

import android.graphics.Paint
import androidx.appcompat.widget.AppCompatTextView

/**
 * Created by Pranay Patel on 01/10/19.
 */

/**
 * Make under line on TextView
 * @receiver AppCompatTextView
 */
fun AppCompatTextView.underLine() {
    paint.flags = paint.flags or Paint.UNDERLINE_TEXT_FLAG
    paint.isAntiAlias = true
}

/**
 * Make strike line on TextView
 * @receiver AppCompatTextView
 */
fun AppCompatTextView.strikeLine() {
    paint.flags = paint.flags or Paint.STRIKE_THRU_TEXT_FLAG
    paint.isAntiAlias = true
}


