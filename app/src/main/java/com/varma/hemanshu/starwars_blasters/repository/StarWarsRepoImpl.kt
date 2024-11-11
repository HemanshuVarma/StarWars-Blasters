package com.varma.hemanshu.starwars_blasters.repository

import android.content.Context
import com.varma.hemanshu.starwars_blasters.model.MatchDetailsResponse
import com.varma.hemanshu.starwars_blasters.model.PlayersInfoResponse

class StarWarsRepoImpl : BaseRepo() {

    fun getPlayersList(context: Context): PlayersInfoResponse? {
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
    }

}