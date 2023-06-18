package com.example.kotlingraphics_v1.operations

import android.graphics.Canvas
import android.graphics.Paint

interface Drawable {
    fun drawObject(canvas: Canvas, paint: Paint)
}