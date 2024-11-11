package com.varma.hemanshu.starwars_blasters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.varma.hemanshu.starwars_blasters.R
import com.varma.hemanshu.starwars_blasters.databinding.ItemMatchDetailsBinding
import com.varma.hemanshu.starwars_blasters.model.MatchDetails

class PlayerMatchDetailAdapter(val data: List<MatchDetails?>?, val id: Int) :
    RecyclerView.Adapter<PlayerMatchDetailAdapter.PlayerMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerMatchViewHolder {
        val binding =
            ItemMatchDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerMatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerMatchViewHolder, position: Int) {
        data?.get(position)?.let { holder.bindViews(it, id = id) }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class PlayerMatchViewHolder(private val holder: ItemMatchDetailsBinding) :
        ViewHolder(holder.root) {

        fun bindViews(items: MatchDetails, id: Int) {
            var selectedPlayer: Int? = 0
            if (id == items.player1?.id) {
                selectedPlayer = items.player1?.id
            } else {
                selectedPlayer = items?.player2?.id
            }
            if (selectedPlayer == items.player1?.id) {
                if ((items.player1?.score ?: 0) > (items.player2?.score ?: 0)) {
                    holder.container.setBackgroundColor(holder.root.context.getColor(R.color.green))
                } else if ((items.player1?.score ?: 0) < (items.player2?.score ?: 0)) {
                    holder.container.setBackgroundColor(holder.root.context.getColor(R.color.red))
                } else {
                    holder.container.setBackgroundColor(holder.root.context.getColor(R.color.white))
                }
            } else {
                if ((items.player2?.score ?: 0) > (items.player1?.score ?: 0)) {
                    holder.container.setBackgroundColor(holder.root.context.getColor(R.color.green))
                } else if ((items.player2?.score ?: 0) < (items.player1?.score ?: 0)) {
                    holder.container.setBackgroundColor(holder.root.context.getColor(R.color.red))
                } else {
                    holder.container.setBackgroundColor(holder.root.context.getColor(R.color.white))
                }
            }

            holder.tvPoints.text = "${items.player1?.score} - " + items.player2?.score
            holder.tvPlayerName1.text = items.player1?.name
            holder.tvPlayerName2.text = items.player2?.name
        }
    }
}



