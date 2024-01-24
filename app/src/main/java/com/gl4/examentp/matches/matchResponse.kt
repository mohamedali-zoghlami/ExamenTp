package com.gl4.tpfinal.matches

data class matchResponse(
    val competition: Competition,
    val filters: Filters,
    val matches: List<Matche>,
    val resultSet: ResultSet
)