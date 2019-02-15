package com.bext

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

/**
 * Created by Birju Vachhani on 15/02/19.
 */

/**
 * Creates a bitmap from the view by drawing it on canvas.
 * DrawingCache is not used as it is deprecated in api 28
 * @return bitmap created from the view
 * */
fun View.asBitmap(): Bitmap =
    Bitmap.createBitmap(layoutParams.width, layoutParams.height, Bitmap.Config.ARGB_8888).also { bitmap ->
        draw(Canvas(bitmap))
    }

@RequiresApi(Build.VERSION_CODES.O)
fun View.copyPixels(view: View, func: (Bitmap) -> Unit) {
    val viewContext = context
    when (viewContext) {
        is Activity -> copyPixelsUsingPixelApi(viewContext.window, view, func)
        is Fragment -> copyPixelsUsingPixelApi(viewContext.requireActivity().window, view, func)
        else -> throw Exception("Unable to get window from provided view..")
    }
}