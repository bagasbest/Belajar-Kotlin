package com.bagasbest.fundamental2.myReadWriteFile

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.OutputStreamWriter

internal object FileHelper  {
    private val TAG = FileHelper::class.java.simpleName

    // To save input from user
    fun writeToFile(fileModel: FileModel, context: Context) {
        try {
            val outputStreamWriter = OutputStreamWriter(context.openFileOutput(fileModel.filename.toString(), Context.MODE_PRIVATE))
            outputStreamWriter.write(fileModel.data.toString())
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e(TAG, "File write failed : ", e)
        }
    }

    // to read data from user selected
    fun readFromFile(context: Context, filename: String): FileModel {
        val fileModel = FileModel()

        try {
            val inputStream = context.openFileInput(filename)

            if(inputStream != null) {
                val receiveString = inputStream.bufferedReader().use(BufferedReader::readText)
                inputStream.close()
                fileModel.data = receiveString
                fileModel.filename = filename
            }
        } catch (e: IOException) {
            Log.e(TAG, "Cannot read failed : ", e)
        } catch (e: FileNotFoundException) {
            Log.e(TAG, "File not found : ", e)
        }
        return fileModel
    }
}