package com.varma.hemanshu.starwars_blasters.model

data class MatchDetailsResponse(
    val data: List<MatchDetails>
)

data class MatchDetails(
    val player1: Player1? = null,
    val player2: Player2? = null,
    val match: Int? = null,
)
