package com.gl4.examentp.standing

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gl4.examentp.RetrofitHelper
import com.gl4.examentp.standing.data.standingResponse
import com.gl4.tpfinal.matches.matchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class DashboardViewModel : ViewModel() {

    private val matches = MutableLiveData<standingResponse>();
    var game : LiveData<standingResponse> = matches
    var place="PL";

    init {
        getMatches(place)
    }
    private fun getMatches(place:String)
    {
       RetrofitHelper.retrofitService.getStandings(place).enqueue(
               object : Callback<standingResponse> {
                   override fun onResponse(
                       call: Call<standingResponse>,
                       response: Response<standingResponse>
                   ) {
                       if(response.isSuccessful){
                           matches.value = response.body()
                           Log.d("YourTag", matches.value.toString())

                       }
                   }

                   override fun onFailure(call: Call<standingResponse>, t: Throwable) {
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