package com.example.kotlingraphics_v1.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceHolder.Callback
import android.view.SurfaceView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.kotlingraphics_v1.model.Engine

class RenderView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet) :
    SurfaceView(context, attributeSet) {

    override fun performClick(): Boolean {
        return super.performClick()
    }
}