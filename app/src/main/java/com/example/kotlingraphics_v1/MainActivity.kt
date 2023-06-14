package com.example.kotlingraphics_v1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlingraphics_v1.databinding.ActivityMainBinding
import com.example.kotlingraphics_v1.models.ScreenMode
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var renderView: RenderView

    private var engine = Engine()
    private var currentScreenMode: ScreenMode = ScreenMode.RENDER


    private fun deployUi() {
        // Create our renderScreen
        renderView = binding.renderView
        renderView.engine = engine
    }

    private fun setUiBehaviour() {
        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> changeScreenMode(ScreenMode.RENDER)
                R.id.page_2 -> changeScreenMode(ScreenMode.OPTIONS)
                else -> false
            }
        }

        renderView.setOnTouchListener { view, motionEvent ->
            view.performClick()
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.e("Engine", "${motionEvent.x}")
                    engine.calculate()
                }

                else -> {

                }
            }
            true
        }
    }

    private fun changeScreenMode(mode: ScreenMode): Boolean {
        var changeCompleted = false
        if (currentScreenMode != mode) {
            currentScreenMode = mode
            when (mode) {
                ScreenMode.RENDER -> {
                    renderView.visibility = View.VISIBLE
                    renderView.resumeRender()
                    changeCompleted = true
                }

                ScreenMode.OPTIONS -> {
                    renderView.pauseRender()
                    renderView.visibility = View.GONE
                    changeCompleted = true
                }

                else -> changeCompleted = false
            }
        }
        return changeCompleted
    }

    private fun showAlertDialog() {
        val dlg = MaterialAlertDialogBuilder(binding.root.context)
            .setTitle("Alert")
            .setMessage("Msg")
            .setNeutralButton("Ok", null)
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialising binding
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        deployUi()
        setUiBehaviour()
    }

    override fun onResume() {
        super.onResume()
        renderView.resumeRender() // Запускаем поток render
    }

    override fun onPause() {
        super.onPause()
        renderView.pauseRender()
    }
}