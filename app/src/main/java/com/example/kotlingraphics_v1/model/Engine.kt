package com.example.kotlingraphics_v1.model

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.media.MediaParser.SeekPoint

class Engine {
    var frameCount = 0;
    private var fps = 0

    private val listObjects = mutableListOf<DObject>()
    val currentPos: PointF = PointF(0.0f, 0.0f)
    fun addSpaceObject(spaceObject: DObject) {
        this.listObjects.add(spaceObject)
    }

    fun calculate() {
        listObjects.forEach { spaceObject ->
            if (spaceObject is DPlanet) {
                spaceObject.rotate(0.01f)
            }
        }
    }

    fun fpsUpdate() {
        fps = frameCount * 4
        frameCount = 0
    }

    fun drawAllObjects(canvas: Canvas, paint: Paint) {
        DBackground.GreyBack.drawObject(canvas, paint)
        DText(fps.toString()).create().drawObject(canvas, paint)

        listObjects.forEach {
            it.drawObject(canvas, paint)
        }
    }

    fun addPoint(pointF: PointF){
        listObjects.add(DPoint(pointF.x,pointF.y))
    }
}