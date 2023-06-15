package com.example.kotlingraphics_v1.model

import com.example.kotlingraphics_v1.operations.SetRotation
import kotlin.math.cos
import kotlin.math.sin

class SpacePlanet() : SpaceObject(), SetRotation {
    var radius: Float = 4.0f
    var orbitCenter: SpaceObject? = null

    constructor(x: Float, y: Float) : this() {
        this.xPos = x
        this.yPos = y
        this.zPos = 0.0f
    }

    override fun rotate(angle: Float) {
        if (orbitCenter != null) {
            val c = cos(angle)
            val s = sin(angle)
            val xNew = xPos * c - yPos * s
            val yNew = xPos * s + yPos * c
            this.xPos = xNew
            this.yPos = yNew
        }
        else{
            this.yPos++
        }
    }
}