package com.example.kotlingraphics_v1.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class DBackground() {
    companion object GreyBack: DObject() {
        override fun drawObject(canvas: Canvas, paint: Paint) {
            paint.color= Color.DKGRAY
            paint.style = Paint.Style.FILL
            canvas.drawRect(0.0f,0.0f,canvas.width.toFloat(),canvas.height.toFloat(),paint)
        }
    }

}