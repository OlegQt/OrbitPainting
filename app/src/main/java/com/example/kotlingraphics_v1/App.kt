package com.example.kotlingraphics_v1

import android.app.Application
import android.content.Context
import android.os.Looper
import com.example.kotlingraphics_v1.activities.MainActivity
import java.util.logging.Handler

class App: Application() {
    override fun onCreate() {
        super.onCreate()
    }

    fun getInstance():App{
        return if (instance!=null) instance!!
        else App()
    }
    companion object{
        var instance:App? =null
    }
}