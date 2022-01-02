package nethical.cold.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader

class FileUtils {

    fun save(filename: String, data: String, context: Context) {
        var fos: FileOutputStream? = null

        try {
            fos = context.openFileOutput(filename, MODE_PRIVATE)
            fos.write(data.toByteArray())
        } catch(e: Exception) {
            e.printStackTrace()
        } finally {
            fos?.close()
        }

    }

    fun read(filename: String, data: String, context: Context): String?{
        var fileInputStream: FileInputStream? = null
        fileInputStream = context.openFileInput(filename)
        var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder: StringBuilder = StringBuilder()
        var text: String? = null
        while (run {
                text = bufferedReader.readLine()
                text
            } != null) {
            stringBuilder.append(text)
        }
        return text
    }

}