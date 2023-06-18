package com.example.kotlingraphics_v1.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

open class DText(var txt:String):DObject() {
    override fun drawObject(canvas: Canvas, paint: Paint) {
        paint.color= Color.BLACK
        paint.style = Paint.Style.FILL
        paint.textSize = 30.0f
        canvas.drawText(txt,10.0f,40.0f,paint)
    }
    fun create():DText = this
    companion object{
        val Ar =object :DText("djjj"){}
    }
}