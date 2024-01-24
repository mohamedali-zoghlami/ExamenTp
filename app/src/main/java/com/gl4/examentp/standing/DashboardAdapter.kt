package com.gl4.examentp.standing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gl4.examentp.R
import com.gl4.examentp.standing.data.standingResponse

class DashboardAdapter(private val matches:standingResponse) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.standing, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.infos.text ="${matches.standings[0].table[position].points} pts/ ${matches!!.standings[0].table[position].playedGames} games"
        holder.names.text = matches.standings[0].table[position].team.shortName;
        holder.rank.text = matches.standings[0].table[position].position.toString();
    }

    override fun getItemCount(): Int {
        if (matches != null)
            if(matches.standings!=null)
                return matches.standings[0].table.size
        return 0;
    }
}