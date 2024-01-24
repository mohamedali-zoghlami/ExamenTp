package com.gl4.examentp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val baseUrl ="https://api.football-data.org/v4/"

    private val retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
     val retrofitService : FootballApi by lazy { retrofit.create(FootballApi::class.java) }

}