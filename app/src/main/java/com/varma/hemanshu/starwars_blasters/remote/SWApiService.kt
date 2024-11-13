package com.varma.hemanshu.starwars_blasters.remote

import com.varma.hemanshu.starwars_blasters.model.MatchDetailsResponse
import com.varma.hemanshu.starwars_blasters.model.PlayersInfoResponse
import retrofit2.Response
import retrofit2.http.GET

interface SWApiService {

    @GET("b/IKQQ")
    suspend fun getPlayerInfo(): Response<PlayersInfoResponse>

    @GET("b/JNYL")
    suspend fun getMatchDetails(): Response<MatchDetailsResponse>
}