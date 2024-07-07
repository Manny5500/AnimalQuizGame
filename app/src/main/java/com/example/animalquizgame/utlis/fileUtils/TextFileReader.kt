package com.example.animalquizgame.utlis.fileUtils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

class TextFileReader: ReadTextFile {
    lateinit var arrayList:ArrayList<Int>
    override fun readTextFile(filename: String, context: Context) {
        arrayList = ArrayList()
        val fileInputStream = context.openFileInput(filename)
        val reader = BufferedReader(InputStreamReader(fileInputStream))
        var line: String? = reader.readLine()
        while (line != null) {
            arrayList.add(line.toInt())
            line = reader.readLine()
        }
        reader.close()
    }
}