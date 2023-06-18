package com.example.kotlingraphics_v1

import android.graphics.PointF
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.kotlingraphics_v1.model.Engine
import com.example.kotlingraphics_v1.view.DrawThread
import com.example.kotlingraphics_v1.view.RenderView
import com.google.android.material.snackbar.Snackbar

class GameViewModel(private val renderView: RenderView, private val handler: Handler) {
    private val gameEngine: Engine = Engine()
    private var drawThread = DrawThread(renderView.holder, gameEngine)

    private var timeClickDown: Long = 0L
    private var clickPos: PointF = PointF()

    init {
    }

    fun startGame() {
        if (drawThread.isAlive) {
            //
        } else {
            drawThread = DrawThread(renderView.holder, gameEngine)
            drawThread.setRunning(true)
            drawThread.start()
        }

        // Запуск непрерывного расчета fps
        fpsTimerStart()
    }

    fun pauseGame() {
        drawThread.setRunning(false)
        try {
            drawThread.join()

            // Выключаем таймер расчета fps и др.
            handler.removeCallbacksAndMessages(null)
        } catch (e: InterruptedException) {
            Log.e("Engine", e.message.toString())
        }
    }

    fun onLMouseDown(clickPos: PointF) {
        timeClickDown = System.currentTimeMillis()
        this.clickPos = clickPos
    }

    fun onLMouseUp() {
        val clickTime = System.currentTimeMillis() - timeClickDown
        this.leftMouseClick(clickTime)
    }

    private fun leftMouseClick(clickTime: Long) {
        val message = with(StringBuilder()) {
            append("Click time ${clickTime.toString()}")
            append("\n")
            append("XPos ${clickPos.x} ")
            append("\n")
            append("YPos ${clickPos.y} ")
        }.toString()

        gameEngine.addPoint(clickPos)

        Snackbar.make(renderView, message, Snackbar.LENGTH_INDEFINITE)
            .setTextMaxLines(5)
            .setAction("Ok") {}
            .show()
    }

    private fun fpsTimerStart() {
        // Вызываем функцию расчета fps через каждые 250 мс
        val timerFpsRunnable = object : Runnable {
            override fun run() {
                gameEngine.fpsUpdate()
                handler.postDelayed(this, 250)
            }
        }
        handler.post(timerFpsRunnable)
    }
}
