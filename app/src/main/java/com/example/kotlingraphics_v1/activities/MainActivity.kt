package com.example.kotlingraphics_v1.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlingraphics_v1.R
import com.example.kotlingraphics_v1.model.Engine
import com.example.kotlingraphics_v1.databinding.ActivityMainBinding
import com.example.kotlingraphics_v1.model.SpacePlanet
import com.example.kotlingraphics_v1.model.SpacePoint
import com.example.kotlingraphics_v1.enum.ScreenMode
import com.example.kotlingraphics_v1.view.RenderView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var renderView: RenderView

    private var engine = Engine()
    private var currentScreenMode: ScreenMode = ScreenMode.RENDER


    private fun deployUi() {
        // Create our renderScreen
        renderView = binding.renderView
        //renderView.engine = engine
    }

    private fun setUiBehaviour() {
        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> changeScreenMode(ScreenMode.RENDER)
                R.id.page_2 -> changeScreenMode(ScreenMode.OPTIONS)
                else -> false
            }
        }

        renderView.setOnTouchListener { view, event ->
            view.performClick()

            // event = motionEvent
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.e("Engine", "${event.x}")

                    val planet = SpacePlanet(event.x, event.y)
                    planet.orbitCenter = SpacePoint()
                    engine.addSpaceObject(planet)

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


        val h = Handler(Looper.getMainLooper())
        h.postDelayed({
                      h.postDelayed({showAlertDialog()},1000)
        },5000)

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