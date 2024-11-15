package com.varma.hemanshu.starwars_blasters.repository

import android.content.Context
import com.varma.hemanshu.starwars_blasters.model.MatchDetails
import com.varma.hemanshu.starwars_blasters.model.PlayerInfo
import com.varma.hemanshu.starwars_blasters.remote.SWApiService
import com.varma.hemanshu.starwars_blasters.utils.UiState
import retrofit2.Response

class StarWarsRepoImpl(private val apiService: SWApiService) : StarWarsRepo {

    override suspend fun getPlayerInfo(): UiState<List<PlayerInfo>> {
        return try {
            val response = apiService.getPlayerInfo()
            if (response.isSuccessful) {
                response.body()?.let {
                    UiState.Success(it)
                } ?: UiState.Error("No Data Available")
            } else {
                UiState.Error("Error: ${response.code()}")
            }
        } catch (exception: Exception) {
            UiState.Error("Exception: ${exception.message}")
        }
    }

    override suspend fun getMatchDetailsInfo(): UiState<List<MatchDetails>> {
        return try {
            val response = apiService.getMatchDetails()
            if (response.isSuccessful) {
                response.body()?.let {
                    UiState.Success(it)
                } ?: UiState.Error("No Data Available")
            } else {
                UiState.Error("Error: ${response.code()}")
            }
        } catch (exception: Exception) {
            UiState.Error("Exception: ${exception.message}")
        }
    }

    /*fun getPlayersList(context: Context): PlayersInfoResponse? {
        val playersList =
            fetchDataFromAssets(context, "StarWarsPlayers.json", PlayersInfoResponse::class.java)
        val playerMatchInfo = fetchDataFromAssets(
            context,
            "StarWarsMatches.json",
            MatchDetailsResponse::class.java
        )?.data

        val playersMap = playersList?.data?.associateBy { it?.id }

        playersList?.data?.forEach { playerInfo ->
            playerInfo?.let { info ->
                val filteredList = playerMatchInfo?.filter {
                    it?.player1?.id == info.id || it?.player2?.id == info.id
                }

                var totalPlay = 0

                filteredList?.forEach { match ->
                    match?.let { m ->
                        when {
                            m.player1?.id == info.id -> {
                                m.player1?.name = info.name
                                totalPlay += calculatePoints(m.player1?.score, m.player2?.score)
                            }

                            m.player2?.id == info.id -> {
                                m.player2?.name = playersMap?.get(m.player2?.id)?.name
                                totalPlay += calculatePoints(m.player2?.score, m.player1?.score)
                            }
                        }
                    }
                }

                info.totalPlay = totalPlay
                info.listGamePlay = filteredList
            }
        }

        return playersList
    }*/

    /*    fun calculatePoints(score1: Int?, score2: Int?): Int {
            return when {
                (score1 ?: 0) > (score2 ?: 0) -> 3
                (score1 ?: 0) == (score2 ?: 0) -> 1
                else -> 0
            }
        }*/

}