package com.example.kotlingraphics_v1.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class DArcBar(x: Float, y: Float) : DObject(x, y, 0.0f) {
    constructor() : this(0.0f, 0.0f)

    private var rad: Float = 20.0f
    private var sweepAngle: Float = 0.0f
    private var startAngle: Float = 0.0f
    private var phase: Boolean = true

    override fun drawObject(canvas: Canvas, paint: Paint) {
        paint.color = Color.MAGENTA
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 20.0f

        // calculate rect
        val arcRect: RectF = RectF(xPos - rad, yPos - rad, xPos + rad, yPos + rad)
        // Draw Arc
        canvas.drawArc(arcRect, startAngle, sweepAngle, true, paint)
    }

    fun setRad(newRadius:Float){
        this.rad = newRadius
    }

    fun calc(maxY: Float) {
        if (phase) {
            sweepAngle++
            if (sweepAngle == 360.0f) phase = false
        }
        if (phase == false && sweepAngle > 0) {
            startAngle++
            sweepAngle = 360.0f-startAngle
        }
        else if(phase == false && sweepAngle == 0.0f){
            phase=true
            startAngle = 0.0f
            sweepAngle=0.0f
        }
    }
}