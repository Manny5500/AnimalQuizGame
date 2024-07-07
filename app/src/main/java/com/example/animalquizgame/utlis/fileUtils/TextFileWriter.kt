package com.example.animalquizgame.utlis.fileUtils

import android.content.Context
import java.io.FileOutputStream

class TextFileWriter : WriteTextFile {
    override fun writeTextFile(fileName: String, context: Context, content: String) {
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = context.openFileOutput(fileName, Context.MODE_APPEND)
            fileOutputStream.write(content.toByteArray())
            fileOutputStream.write("\n".toByteArray()) // append newline after content
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}