package com.catata.masterdetailexample.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun String.toBitmap(context: Context): Bitmap {
    val id = context.resources.getIdentifier(
        this, //Something like "batman"
        "raw",
        context.packageName
    )

    val inputStream =context.resources.openRawResource(id)

    return BitmapFactory.decodeStream(inputStream)
}