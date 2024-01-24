package com.gl4.examentp

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.examentp.players.Players
import com.gl4.examentp.standing.Standing
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    private val matchModel:DashboardViewModel= DashboardViewModel();

    lateinit var btn:Button;
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView);
        recyclerView.layoutManager = LinearLayoutManager(this)
        matchModel.changeLocation("PL");
        matchModel.game.observe(this){
            recyclerView.adapter = it?.let { it1 -> DashboardAdapter(it1) }
        }
        val leagues= listOf<String>("Premier League","La liga","BundesLiga","Ligue 1","Seria A","Eredivisie");
        val codes= listOf<String>("PL","PD","BL1","FL1","SA","DED")
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, leagues)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    matchModel.changeLocation(codes[position])
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }
        if(!checkConnection(this))
        {
            Toast.makeText(applicationContext, "No connexion", Toast.LENGTH_LONG).show()
        }
        val btn=findViewById<Button>(R.id.button);
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val current = LocalDateTime.now().format(formatter)
        btn.text=current;
        btn.setOnClickListener{
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
                val date = dateFormatter.format(Date(it))
                if(checkConnection(this))
                {matchModel.changeDay(date)}
                else
                {
                    Toast.makeText(applicationContext, "No connexion", Toast.LENGTH_LONG).show()
                }
                btn.text=date;
            }

            datePicker.addOnNegativeButtonClickListener {
                Toast.makeText(this, "${datePicker.headerText} is cancelled", Toast.LENGTH_LONG).show()
            }

            datePicker.addOnCancelListener {
                Toast.makeText(this, "Date Picker Cancelled", Toast.LENGTH_LONG).show()
            }
        }
        val standing=findViewById<Button>(R.id.standing);
        standing.setOnClickListener{
            var intent =Intent(this,Standing::class.java)
            var place=spinner.selectedItem.toString()
            var index=leagues.indexOf(place);
            if(checkConnection(this))
            {intent.putExtra("place",place)
                intent.putExtra("code",codes[index])
                startActivity(intent);
            }
            else
            {
                Toast.makeText(applicationContext, "No connexion", Toast.LENGTH_LONG).show()
            }
        }
        val players=findViewById<Button>(R.id.scorer);
        players.setOnClickListener{
            var intent =Intent(this,Players::class.java)
            var place=spinner.selectedItem.toString()
            var index=leagues.indexOf(place);
            if(checkConnection(this))
            {intent.putExtra("place",place)
                intent.putExtra("code",codes[index])
                startActivity(intent);
            }
            else
            {
                Toast.makeText(applicationContext, "No connexion", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun checkConnection(context:Context): Boolean {
        val connectivity= context.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(connectivity!=null){
            val info=connectivity!!.activeNetworkInfo
            if(info!=null)
            {
                if(info!!.state == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false
    }

}