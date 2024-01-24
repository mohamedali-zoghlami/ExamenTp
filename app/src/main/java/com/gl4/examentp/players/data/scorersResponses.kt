package com.gl4.examentp.players.data

data class scorersResponses(
    val competition: Competition,
    val count: Int,
    val filters: Filters,
    val scorers: List<Scorer>,
    val season: Season
)