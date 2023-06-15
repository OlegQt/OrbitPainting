package com.example.kotlingraphics_v1.model

import android.graphics.PointF

class Engine {
    val listObjects = mutableListOf<SpaceObject>()
    val currentPos:PointF = PointF(0.0f,0.0f)
    fun addSpaceObject(spaceObject: SpaceObject){
        this.listObjects.add(spaceObject)
    }
    fun calculate(){
        listObjects.forEach { spaceObject ->
            if (spaceObject is SpacePlanet) {
                spaceObject.rotate(0.01f)
            }
        }
    }
}