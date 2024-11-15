package com.varma.hemanshu.starwars_blasters.remote

import com.varma.hemanshu.starwars_blasters.model.MatchDetails
import com.varma.hemanshu.starwars_blasters.model.PlayerInfo
import retrofit2.Response
import retrofit2.http.GET

interface SWApiService {

    @GET("b/IKQQ")
    suspend fun getPlayerInfo(): Response<List<PlayerInfo>>

    @GET("b/JNYL")
    suspend fun getMatchDetails(): Response<List<MatchDetails>>
}