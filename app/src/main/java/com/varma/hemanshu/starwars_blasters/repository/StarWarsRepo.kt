package com.varma.hemanshu.starwars_blasters.repository

import android.content.Context
import com.google.gson.Gson
import com.varma.hemanshu.starwars_blasters.model.MatchDetailsResponse
import com.varma.hemanshu.starwars_blasters.model.PlayerInfo
import com.varma.hemanshu.starwars_blasters.utils.UiState
import retrofit2.Response
import java.io.IOException
import java.nio.charset.StandardCharsets

interface StarWarsRepo {

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

    suspend fun getPlayerInfo(): UiState<List<PlayerInfo>>

    suspend fun getMatchDetailsInfo(): UiState<MatchDetailsResponse>
}