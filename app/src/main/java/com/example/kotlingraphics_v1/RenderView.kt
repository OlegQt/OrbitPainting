package com.example.kotlingraphics_v1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.SurfaceView

class RenderView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet) :
    SurfaceView(context, attributeSet) {

    private var isRunning = false
    private var renderThread: Thread? = null
    lateinit var engine: Engine

    private val paint = Paint()

    private fun render() {
        val frameStartTime = System.currentTimeMillis()
        draw()
        // Store the fps in millis
        val frameTime = System.currentTimeMillis() - frameStartTime
    }

    fun resumeRender() {
        isRunning = true
        startRenderThread()
    }

    fun pauseRender() {
        isRunning = false
        stopRenderThreat()
    }

    private fun startRenderThread() {
        if (renderThread == null) {
            renderThread = Thread {
                while (isRunning) {
                    render()
                }
            }
        }
        renderThread?.start()
    }

    private fun stopRenderThreat() {
        try {
            // Stop the thread (rejoin the main thread)
            renderThread?.join()
            renderThread = null
            // Logging
        } catch (error: InterruptedException) {

        }
    }

    private fun draw() {
        if (this.holder.surface.isValid) {
            val canvas = holder.lockCanvas()
            if (canvas !== null) {
                paint.color = Color.GRAY
                val rect = RectF(0.0f, 0.0f, canvas.width.toFloat(), canvas.height.toFloat())
                canvas.drawRect(rect, paint)
                drawEngine(canvas)

                holder.unlockCanvasAndPost(canvas)
            }
        }
    }

    private fun drawEngine(canvas: Canvas){
        paint.color = Color.YELLOW
        paint.style = Paint.Style.FILL
        canvas.drawRect(engine.rect, paint)
    }
}