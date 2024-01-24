package com.gl4.examentp.standing.data

data class standingResponse(
    val area: Area,
    val competition: Competition,
    val filters: Filters,
    val season: Season,
    val standings: List<Standing>
)