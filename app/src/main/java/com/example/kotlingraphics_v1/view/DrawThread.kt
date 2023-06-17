package com.example.kotlingraphics_v1.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.Log
import android.view.SurfaceHolder
import com.example.kotlingraphics_v1.model.Engine

class DrawThread(private var surfaceHolder: SurfaceHolder, private var gameEngine: Engine) :
    Thread() {
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
        //gameEngine.frameCount =0

        while (isRunning) {
            val currentTime = System.currentTimeMillis()
            val elapsedTime = currentTime - previousTime
            previousTime = currentTime

            gameEngine.frameCount++

            if (surfaceHolder.surface.isValid) {
                try {
                    canvas = surfaceHolder.lockCanvas()
                } finally {
                    synchronized(surfaceHolder) {
                        val paint = Paint()
                        paint.style = Paint.Style.FILL
                        paint.color = Color.DKGRAY
                        var rect =
                            RectF(0.0f, 0.0f, canvas.width.toFloat(), canvas.height.toFloat())
                        canvas.drawRect(rect, paint)
                        paint.color = Color.BLACK
                        paint.textSize = 30.0f
                        canvas.drawText("FPS ${gameEngine.fps}", 8.0f, 60.0f, paint)

                        paint.color = Color.YELLOW
                        rect =  RectF(100.0f, 100.0f, 150.0f, 150.0f)
                        if(gameEngine.temporal) canvas.drawRect(rect,paint)

                        surfaceHolder.unlockCanvasAndPost(canvas)
                    }
                }
            }
        }
    }
}