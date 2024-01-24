package com.gl4.examentp

import com.gl4.examentp.players.data.scorersResponses
import com.gl4.examentp.standing.data.standingResponse
import com.gl4.tpfinal.matches.matchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface FootballApi {
    @Headers("X-Auth-Token: d53bdcdd50d342fcbb4c9ee7257463ca")
    @GET("competitions/{competition}/matches")
    fun getMatches(@Path("competition") code:String,@Query("dateTo") date : String,@Query("dateFrom") date2:String) : Call<matchResponse>

    @Headers("X-Auth-Token: d53bdcdd50d342fcbb4c9ee7257463ca")
    @GET("competitions/{competition}/standings")
    fun getStandings(@Path("competition") code:String) : Call<standingResponse>

    @Headers("X-Auth-Token: d53bdcdd50d342fcbb4c9ee7257463ca")
    @GET("competitions/{competition}/scorers")
    fun getPlayers(@Path("competition") code:String) : Call<scorersResponses>

}