package com.varma.hemanshu.starwars_blasters.repository

import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.nio.charset.StandardCharsets

open class BaseRepo {

    fun <T> fetchDataFromAssets(context: Context, fileName: String, classOfT: Class<T>): T? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val byteArray = ByteArray(size)
            inputStream.read(byteArray)
            inputStream.close()
            Gson().fromJson(String(byteArray, StandardCharsets.UTF_8), classOfT)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun calculatePoints(score1: Int?, score2: Int?): Int {
        return when {
            (score1 ?: 0) > (score2 ?: 0) -> 3
            (score1 ?: 0) == (score2 ?: 0) -> 1
            else -> 0
        }
    }
}