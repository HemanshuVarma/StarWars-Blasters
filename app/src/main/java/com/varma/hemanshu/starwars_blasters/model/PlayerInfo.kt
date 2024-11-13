package com.varma.hemanshu.starwars_blasters.model

data class PlayerInfo(
    val id: Int,
    val name: String,
    val icon: String,

    var totalPlay: Int? = null,
    var listGamePlay: List<MatchDetails?>? = ArrayList()
)