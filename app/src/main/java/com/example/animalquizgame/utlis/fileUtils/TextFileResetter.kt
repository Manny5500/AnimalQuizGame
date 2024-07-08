package com.example.animalquizgame.utlis.fileUtils

import android.content.Context
import java.io.FileOutputStream

class TextFileResetter : ResetTextFile {
    override fun resetTextFile(fileName: String, context: Context) {
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            fileOutputStream.write("".toByteArray())
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}