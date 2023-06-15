package com.example.kotlingraphics_v1.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.Log
import android.view.SurfaceHolder

class DrawThread(var surfaceHolder: SurfaceHolder) : Thread() {
    private var previousTime: Long = 0
    private var isRunning: Boolean = false
    private var canvas: Canvas = Canvas()

    init {
        this.previousTime = System.currentTimeMillis()
    }

    fun setRunning(run: Boolean) {
        this.isRunning = run
    }

    override fun run() {
        while (isRunning) {
            val currentTime = System.currentTimeMillis()
            val elapsedTime = System.currentTimeMillis()
            previousTime = currentTime

            if (surfaceHolder.surface.isValid) {
                try {
                    canvas = surfaceHolder.lockCanvas()
                } finally {
                    synchronized(surfaceHolder) {

                        val paint = Paint()
                        paint.style = Paint.Style.FILL
                        paint.color = Color.YELLOW

                        val rect = RectF(0.0f, 0.0f, canvas.width.toFloat(), canvas.height.toFloat())
                        canvas.drawRect(rect,paint)

                        surfaceHolder.unlockCanvasAndPost(canvas)
                    }
                }
            }
        }
    }


}