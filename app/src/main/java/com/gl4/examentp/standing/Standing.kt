package com.gl4.examentp.standing

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.examentp.R

class Standing : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    private val matchModel: DashboardViewModel = DashboardViewModel();
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standing)
        val place=intent.getStringExtra("place")
        val code=intent.getStringExtra("code")
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView);
        recyclerView.layoutManager = LinearLayoutManager(this)
        val title=findViewById<TextView>(R.id.titleLeague);
        title.text="League : $place";
        if (code != null) {
            matchModel.changeLocation(code)
        }
        else
        {   matchModel.changeLocation("PL")
        }
        matchModel.game.observe(this){
            recyclerView.adapter = it?.let { it1 -> DashboardAdapter(it1) }
        }
    }
}