package com.example.kotlingraphics_v1.model

class SpacePoint() :SpaceObject() {
    var pointThickness:Double = 4.0
    constructor(x:Float,y:Float):this(){
        this.xPos=x
        this.yPos=y
        this.zPos=0.0f
    }
}