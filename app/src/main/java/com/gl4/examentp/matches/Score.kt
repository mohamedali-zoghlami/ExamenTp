package com.gl4.tpfinal.matches

data class Score(
    val duration: String,
    val fullTime: FullTime,
    val halfTime: HalfTime,
    val winner: String
)