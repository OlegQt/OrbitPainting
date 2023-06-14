package com.example.kotlingraphics_v1.logic

import android.graphics.RectF
import android.util.Log

class Engine {
    val listObjects = mutableListOf<SpaceObject>()
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