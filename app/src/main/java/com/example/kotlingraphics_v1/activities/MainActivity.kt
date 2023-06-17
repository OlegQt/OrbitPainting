package com.example.kotlingraphics_v1.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlingraphics_v1.GameViewModel
import com.example.kotlingraphics_v1.R
import com.example.kotlingraphics_v1.databinding.ActivityMainBinding
import com.example.kotlingraphics_v1.enum.ScreenMode
import com.example.kotlingraphics_v1.view.RenderView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.NavigableMap

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var renderView: RenderView
    private lateinit var game:GameViewModel

    private var badgeN = 0

    private var currentScreenMode: ScreenMode = ScreenMode.RENDER

    private fun deployUi() {
        // Create our renderScreen
        renderView = binding.renderView
        game = GameViewModel(renderView,Handler(Looper.getMainLooper()))
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
                    game.onClick()

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
                    game.startGame()
                    changeCompleted = true
                }

                ScreenMode.OPTIONS -> {
                    game.pauseGame()
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

    private fun increaseBadge(){
        this.badgeN++
        val b = binding.bottomMenu.getOrCreateBadge(R.id.page_1)
        b.number=this.badgeN
        val badgeRunnable = Runnable{
            this.increaseBadge()
        }
        Handler(Looper.getMainLooper()).postDelayed(badgeRunnable,100)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialising binding
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        deployUi()
        setUiBehaviour()

       increaseBadge()


    }

    override fun onResume() {
        super.onResume()
        game.startGame()
    }

    override fun onPause() {
        super.onPause()
        game.pauseGame()
    }
}