package com.gl4.examentp.players

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gl4.examentp.RetrofitHelper
import com.gl4.examentp.players.data.scorersResponses
import com.gl4.examentp.standing.data.standingResponse
import com.gl4.tpfinal.matches.matchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class DashboardViewModel : ViewModel() {

    private val matches = MutableLiveData<scorersResponses>();
    var game : LiveData<scorersResponses> = matches
    var place="PL";

    init {
    }
    private fun getMatches(place:String)
    {
       RetrofitHelper.retrofitService.getPlayers(place).enqueue(
               object : Callback<scorersResponses> {
                   override fun onResponse(
                       call: Call<scorersResponses>,
                       response: Response<scorersResponses>
                   ) {
                       if(response.isSuccessful){
                           matches.value = response.body()
                           Log.d("YourTag", matches.value.toString())

                       }
                   }

                   override fun onFailure(call: Call<scorersResponses>, t: Throwable) {
                   }

               }
               )
    }
    fun changeLocation(comp:String){
        place=comp;
        getMatches(place);
        game = matches;
    }

}