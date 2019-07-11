package com.bext

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

/**
 * Created by Birju Vachhani on 15/02/19.
 */

/*
 * Created by Birju Vachhani on 15 February 2019
 * Copyright © 2019 bext. All rights reserved.
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

/**
 * Creates a bitmap from the view by drawing it on canvas.
 * DrawingCache is not used as it is deprecated in api 28
 * @return bitmap created from the view
 * */
fun View.backgroundAsBitmap(): Bitmap =
    Bitmap.createBitmap(layoutParams.width, layoutParams.height, Bitmap.Config.ARGB_8888).also { bitmap ->
        this.background?.draw(Canvas(bitmap))
    }

/**
 * Creates a bitmap from the view by drawing it on canvas.
 * DrawingCache is not used as it is deprecated in api 28
 * @return bitmap created from the view
 * */
@RequiresApi(Build.VERSION_CODES.M)
fun View.foregroundAsBitmap(): Bitmap =
    Bitmap.createBitmap(layoutParams.width, layoutParams.height, Bitmap.Config.ARGB_8888).also { bitmap ->
        this.foreground?.draw(Canvas(bitmap))
    }

/**
 * Creates a bitmap from the view by drawing it on canvas.
 * DrawingCache is not used as it is deprecated in api 28
 * @return bitmap created from the view
 * */
@RequiresApi(Build.VERSION_CODES.M)
fun ImageView.imageAsBitmap(): Bitmap? = this.drawable?.asBitmap()


/**
 * Uses pixel copy api to copy pixels of a particular view and generate Bitmap from it
 * @receiver View is the from which the bitmap is to be generated
 * @param onSuccess Function1<Bitmap, Unit> is the lambda that will be called
 * when the Bitmap is generated successfully.
 *
 * @throws RuntimeException if it can't retrieve window token
 */
@RequiresApi(Build.VERSION_CODES.O)
@Throws(RuntimeException::class)
fun View.copyPixels(onSuccess: (Bitmap) -> Unit) {
    when (val viewContext = context) {
        is Activity -> copyPixelsUsingPixelApi(viewContext.window, this, onSuccess)
        is Fragment -> copyPixelsUsingPixelApi(viewContext.requireActivity().window, this, onSuccess)
        else -> throw RuntimeException("Unable to get window from provided view..")
    }
}