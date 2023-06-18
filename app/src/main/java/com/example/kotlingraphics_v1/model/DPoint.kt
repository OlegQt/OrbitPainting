package com.example.kotlingraphics_v1.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.kotlingraphics_v1.operations.Drawable

class DPoint() :DObject() {
    private var pointThickness:Float = 6.0f
    private var velosity:Float = 1.0f

    constructor(x:Float,y:Float):this(){
        this.xPos=x
        this.yPos=y
        this.zPos=0.0f
    }

    override fun drawObject(canvas: Canvas, paint: Paint) {
        paint.color=Color.MAGENTA
        paint.style = Paint.Style.FILL
        canvas.drawCircle(xPos,yPos,pointThickness,paint)
    }

    fun fallDown(maxY:Float){
        yPos+=velosity
        velosity+=0.05f

        if(yPos>=maxY) {
            velosity = -velosity
        }
    }
}