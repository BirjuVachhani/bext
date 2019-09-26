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

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.view.PixelCopy
import android.view.View
import android.view.Window
import android.widget.Checkable
import androidx.annotation.RequiresApi
import java.io.Serializable

/*
 * Created by Birju Vachhani on 15 February 2019
 * Copyright © 2019 bext. All rights reserved.
 */

@RequiresApi(Build.VERSION_CODES.O)
internal fun copyPixelsUsingPixelApi(window: Window, view: View, func: (Bitmap) -> Unit) {
    val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
    val locationOfViewInWindow = IntArray(2)
    view.getLocationInWindow(locationOfViewInWindow)
    PixelCopy.request(
        window, Rect(
            locationOfViewInWindow[0], locationOfViewInWindow[1],
            locationOfViewInWindow[0] + view.width, locationOfViewInWindow[1] + view.height
        ), bitmap, { copyResult ->
            if (copyResult == PixelCopy.SUCCESS) {
                func(bitmap)
            }
        }, Handler()
    )
}

internal fun Drawable.asBitmap() =
    Bitmap.createBitmap(
        this.intrinsicWidth,
        intrinsicHeight,
        Bitmap.Config.ARGB_8888
    ).also { bitmap ->
        draw(Canvas(bitmap))
    }

fun Intent.put(vararg pairs: Pair<String, Any>) {
    pairs.forEach {
        when (val value = it.second) {
            // Scalars
            is Boolean -> putExtra(it.first, value)
            is Byte -> putExtra(it.first, value)
            is Char -> putExtra(it.first, value)
            is Double -> putExtra(it.first, value)
            is Float -> putExtra(it.first, value)
            is Int -> putExtra(it.first, value)
            is Long -> putExtra(it.first, value)
            is Short -> putExtra(it.first, value)

            // References
            is Bundle -> putExtra(it.first, value)
            is CharSequence -> putExtra(it.first, value)
            is Parcelable -> putExtra(it.first, value)

            // Scalar arrays
            is BooleanArray -> putExtra(it.first, value)
            is ByteArray -> putExtra(it.first, value)
            is CharArray -> putExtra(it.first, value)
            is DoubleArray -> putExtra(it.first, value)
            is FloatArray -> putExtra(it.first, value)
            is IntArray -> putExtra(it.first, value)
            is LongArray -> putExtra(it.first, value)
            is ShortArray -> putExtra(it.first, value)

            // Reference arrays
            is Array<*> -> {
                val componentType = value::class.java.componentType ?: return
                @Suppress("UNCHECKED_CAST") // Checked by reflection.
                when {
                    Parcelable::class.java.isAssignableFrom(componentType) -> {
                        putExtra(it.first, value as Array<Parcelable>)
                    }
                    String::class.java.isAssignableFrom(componentType) -> {
                        putExtra(it.first, value as Array<String>)
                    }
                    CharSequence::class.java.isAssignableFrom(componentType) -> {
                        putExtra(it.first, value as Array<CharSequence>)
                    }
                    Serializable::class.java.isAssignableFrom(componentType) -> {
                        putExtra(it.first, value)
                    }
                    else -> {
                        val valueType = componentType.canonicalName
                        throw IllegalArgumentException(
                            "Illegal value array type $valueType for it.first \"$it.first\""
                        )
                    }
                }
            }
            // Last resort. Also we must check this after Array<*> as all arrays are serializable.
            is Serializable -> putExtra(it.first, value)
        }
    }
}

/**
 * Sets a checkable view to checked by settings property [Checkable.isChecked] to true
 */
fun Checkable.check() {
    isChecked = true
}

/**
 * Sets a checkable view to unchecked by settings property [Checkable.isChecked] to false
 */
fun Checkable.uncheck() {
    isChecked = false
}
