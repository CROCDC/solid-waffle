package com.crocdc.dataoffline

import android.content.res.AssetManager
import java.io.IOException

fun AssetManager.openJson(fileName: String) = try {
    val file = open(fileName)
    val size: Int = file.available()
    val buffer = ByteArray(size)
    file.read(buffer)
    file.close()
    String(buffer)
} catch (ex: IOException) {
    null
}
