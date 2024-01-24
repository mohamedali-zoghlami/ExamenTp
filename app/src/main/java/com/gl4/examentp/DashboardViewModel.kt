package com.gl4.examentp

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gl4.tpfinal.matches.matchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class DashboardViewModel : ViewModel() {

    private val matches = MutableLiveData<matchResponse>();
    var game : LiveData<matchResponse> = matches
    var place="PL";
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    var date = LocalDateTime.now().format(formatter)
    init {

        getMatches(date,place)
    }
    private fun getMatches(date:String,place:String)
    {
       RetrofitHelper.retrofitService.getMatches(place,date,date).enqueue(
               object : Callback<matchResponse> {
                   override fun onResponse(
                       call: Call<matchResponse>,
                       response: Response<matchResponse>
                   ) {
                       if(response.isSuccessful){
                           matches.value = response.body()

                       }
                   }

                   override fun onFailure(call: Call<matchResponse>, t: Throwable) {

                   }

               }
               )
    }
    fun changeLocation(comp:String){
        place=comp;
        getMatches(date,place);
        game = matches;
    }
    fun changeDay(comp: String)
    {
        date=comp;
        getMatches(date,place)
        game=matches;
    }
}