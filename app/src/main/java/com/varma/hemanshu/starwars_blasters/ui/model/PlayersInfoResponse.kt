package com.varma.hemanshu.starwars_blasters.ui.model

data class PlayersInfoResponse(
    val data: List<PlayerInfo?>? = null
)

data class PlayerInfo(
    val name: String,
    val icon: String,
    val id: Int,

    var totalPlay: Int? = null,
    var listGamePlay: List<MatchDetails?>? = ArrayList()
)