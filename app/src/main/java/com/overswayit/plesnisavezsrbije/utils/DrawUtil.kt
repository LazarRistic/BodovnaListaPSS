package com.overswayit.plesnisavezsrbije.utils

import android.graphics.*
import androidx.annotation.DrawableRes
import com.overswayit.plesnisavezsrbije.MyApp
import com.overswayit.plesnisavezsrbije.R

/**
 * Created by lazarristic on 2019-07-25.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
object DrawUtil {
    fun writeTextOnBitmap(text: String, textColor: Int = MyApp.applicationContext().getColor(R.color.colorPrimary), @DrawableRes resource: Int): Bitmap {
        val textSize = MeasurementUtils.toDensityIndependentPixels(18)
        val workingBitmap = BitmapFactory.decodeResource(MyApp.applicationContext().resources, resource)
        val mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(mutableBitmap)
        val paint = Paint()

        paint.color = textColor
        paint.isAntiAlias = true
        paint.textSize = textSize
        paint.textAlign = Paint.Align.CENTER

        val xPos = canvas.width / 2
        val yPos = (canvas.height / 2 - (paint.descent() + paint.ascent()) / 2).toInt()

        canvas.drawText(text, xPos.toFloat(), yPos.toFloat(), paint)
        return mutableBitmap
    }
}