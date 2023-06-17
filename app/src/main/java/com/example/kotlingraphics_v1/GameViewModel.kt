package com.example.kotlingraphics_v1

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.kotlingraphics_v1.model.Engine
import com.example.kotlingraphics_v1.view.DrawThread
import com.example.kotlingraphics_v1.view.RenderView

class GameViewModel(private val renderView: RenderView, private val handler: Handler){
    private val gameEngine: Engine = Engine()
    private var drawThread = DrawThread(renderView.holder,gameEngine)

    init {
    }

    fun startGame(){
        if(drawThread.isAlive){
        }
        else{
            drawThread = DrawThread(renderView.holder,gameEngine)
            drawThread.setRunning(true)
            drawThread.start()
        }

        // Запуск непрерывного расчета fps
        fpsTimerStart()
    }

    fun pauseGame(){
        drawThread.setRunning(false)
        try {
            drawThread.join()

            // Выключаем таймер расчета fps и др.
            handler.removeCallbacksAndMessages(null)
        }
        catch (e:InterruptedException){
            Log.e("Engine",e.message.toString())
        }
    }

    fun onClick(){
        gameEngine.temporal=true
    }

    fun fpsTimerStart(){
        // Вызываем функцию расчета fps через каждые 250 мс
        val timerFpsRunnable = object :Runnable{
            override fun run() {
                gameEngine.fpsUpdate()
                handler.postDelayed(this,250)
            }
        }
        handler.post(timerFpsRunnable)
    }
}
