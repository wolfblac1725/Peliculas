package com.example.peliculas.app

import android.app.Application

class MyApp : Application() {

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object {

        lateinit var instance: MyApp
    }
}