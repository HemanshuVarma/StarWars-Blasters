package com.varma.hemanshu.starwars_blasters.model

data class PlayersInfoResponse(
    val data: List<PlayerInfo>
)

data class PlayerInfo(
    val name: String,
    val icon: String,
    val id: Int,

    var totalPlay: Int? = null,
    var listGamePlay: List<MatchDetails?>? = ArrayList()
)