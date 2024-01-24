package com.gl4.examentp.players.data

data class Scorer(
    val assists: Int,
    val goals: Int,
    val penalties: Int,
    val playedMatches: Int,
    val player: Player,
    val team: Team
)