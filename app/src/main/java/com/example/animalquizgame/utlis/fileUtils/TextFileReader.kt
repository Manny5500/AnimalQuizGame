package com.example.animalquizgame.utlis.fileUtils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class TextFileReader: ReadTextFile {
    lateinit var arrayList: ArrayList<Int>

    override fun readTextFile(filename: String, context: Context) {
        arrayList = ArrayList()

        if (!context.fileList().contains(filename)) {
            createFile(filename, context)
        }

        val fileInputStream = context.openFileInput(filename)
        val reader = BufferedReader(InputStreamReader(fileInputStream))
        var line: String? = reader.readLine()
        while (line != null) {
            arrayList.add(line.toInt())
            line = reader.readLine()
        }
        reader.close()
    }

    private fun createFile(filename: String, context: Context) {
        try {
            val outputStreamWriter = OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE))
            outputStreamWriter.write("")
            outputStreamWriter.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}