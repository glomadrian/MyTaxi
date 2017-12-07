package com.github.glomadrian.mytaxi.vehiclemap.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.VectorDrawable
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor

fun mapIconFromSvgRes(@DrawableRes resource: Int, context: Context): BitmapDescriptor {
    val bitmap = getBitmap(context, resource)
    return com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap(bitmap)
}

//This method is from: https://stackoverflow.com/a/35559581
private fun getBitmap(context: Context, drawableId: Int): Bitmap {
    val drawable = ContextCompat.getDrawable(context, drawableId)
    return when (drawable) {
        is BitmapDrawable -> BitmapFactory.decodeResource(context.resources, drawableId)
        is VectorDrawable -> getBitmap(drawable)
        else -> throw IllegalArgumentException("unsupported drawable type")
    }
}

//This method is from: https://stackoverflow.com/a/35559581
private fun getBitmap(vectorDrawable: VectorDrawable): Bitmap {
    val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
    vectorDrawable.draw(canvas)
    return bitmap
}