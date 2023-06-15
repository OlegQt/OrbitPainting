package com.example.kotlingraphics_v1.model

abstract class SpaceObject(){
    var xPos:Float = 0.0f
    var yPos:Float =0.0f
    var zPos:Float = 0.0f

    constructor(x:Float,y:Float,z:Float) : this() {
        this.xPos=x
        this.yPos=y
        this.zPos=z
    }

    override fun toString(): String {
        return "Parent class SpaceObject"
    }
}