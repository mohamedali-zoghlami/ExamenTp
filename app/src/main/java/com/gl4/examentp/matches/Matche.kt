package com.gl4.tpfinal.matches

data class Matche(
    val area: Area,
    val awayTeam: AwayTeam,
    val competition: Competition,
    val group: Any,
    val homeTeam: HomeTeam,
    val id: Int,
    val lastUpdated: String,
    val matchday: Int,
    val odds: Odds,
    val referees: List<Referee>,
    val score: Score,
    val season: Season,
    val stage: String,
    val status: String,
    val utcDate: String
)