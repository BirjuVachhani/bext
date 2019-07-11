package com.bext.core

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.view.PixelCopy
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi

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
    Bitmap.createBitmap(this.intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888).also { bitmap ->
        draw(Canvas(bitmap))
    }