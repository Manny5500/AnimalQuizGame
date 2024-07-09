package com.example.animalquizgame

import android.app.Application

class App : Application() {
    companion object {
        const val filename = "score.txt"
    }

    override fun onCreate() {
        super.onCreate()
    }
}