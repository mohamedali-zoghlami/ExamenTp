package com.gl4.examentp.players

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gl4.examentp.R
import com.gl4.examentp.players.data.scorersResponses
import com.gl4.examentp.standing.data.standingResponse

class DashboardAdapter(private val matches:scorersResponses) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val infos: TextView
        val names: TextView
        val rank: TextView

        init {
            infos = itemView.findViewById(R.id.infos)
            names = itemView.findViewById(R.id.names)
            rank = itemView.findViewById(R.id.rank)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.infos.text ="${matches.scorers[position].goals} Gls/ ${matches!!.scorers[position].playedMatches} Games"
        holder.names.text = matches.scorers[position].team.shortName;
        holder.rank.text = matches.scorers[position].player.lastName.toString();
    }

    override fun getItemCount(): Int {
        if (matches != null) return matches.scorers.size
        return 0;
    }
}