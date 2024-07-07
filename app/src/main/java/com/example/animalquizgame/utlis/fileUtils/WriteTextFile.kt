package com.example.animalquizgame.utlis.fileUtils

import android.content.Context

interface WriteTextFile {
    fun writeTextFile(fileName:String, context: Context, content:String)
}