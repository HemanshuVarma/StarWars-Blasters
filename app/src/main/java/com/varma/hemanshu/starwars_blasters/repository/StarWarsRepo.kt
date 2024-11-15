package com.varma.hemanshu.starwars_blasters.repository

import com.varma.hemanshu.starwars_blasters.model.MatchDetails
import com.varma.hemanshu.starwars_blasters.model.PlayerInfo
import com.varma.hemanshu.starwars_blasters.utils.UiState

interface StarWarsRepo {

    suspend fun getPlayerInfo(): UiState<List<PlayerInfo>>

    suspend fun getMatchDetailsInfo(): UiState<List<MatchDetails>>
}