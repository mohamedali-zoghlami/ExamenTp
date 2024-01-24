package com.gl4.examentp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tpfinal.matches.matchResponse

class DashboardAdapter(private val matches:matchResponse) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val score: TextView
        val home: TextView
        val away: TextView

        init {
            score = itemView.findViewById(R.id.names)
            home = itemView.findViewById(R.id.rank)
            away = itemView.findViewById(R.id.infos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.matches, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.score.text ="${matches!!.matches[position].score.fullTime.home} - ${matches!!.matches[position].score.fullTime.away}"
        holder.home.text = "${matches!!.matches[position].homeTeam.name}"
        holder.away.text = "${matches!!.matches[position].awayTeam.name}"
    }

    override fun getItemCount(): Int {
        if (matches != null) return matches.matches.size
        return 0;
    }
}