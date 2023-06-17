package com.example.kotlingraphics_v1.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.Log
import android.view.SurfaceHolder

class DrawThread(private var surfaceHolder: SurfaceHolder) : Thread() {
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
            val elapsedTime = currentTime - previousTime
            previousTime = currentTime

            if (surfaceHolder.surface.isValid) {
                try {
                    canvas = surfaceHolder.lockCanvas()
                } finally {
                    synchronized(surfaceHolder) {

                        val paint = Paint()
                        paint.style = Paint.Style.FILL
                        paint.color = Color.DKGRAY

                        val rect =
                            RectF(0.0f, 0.0f, canvas.width.toFloat(), canvas.height.toFloat())
                        canvas.drawRect(rect, paint)

                        paint.color = Color.BLACK
                        paint.textSize = 50.0f

                        var fps = 5L
                        if (elapsedTime > 0) {
                            fps = 1000 / elapsedTime
                        }

                        canvas.drawText("fps = $fps", 0.0f, 60.0f, paint)

                        surfaceHolder.unlockCanvasAndPost(canvas)
                    }
                }
            }
        }
    }
}