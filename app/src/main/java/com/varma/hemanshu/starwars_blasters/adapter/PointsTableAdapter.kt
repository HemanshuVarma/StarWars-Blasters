package com.varma.hemanshu.starwars_blasters.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.varma.hemanshu.starwars_blasters.databinding.ItemPlayerInfoBinding
import com.varma.hemanshu.starwars_blasters.ui.model.PlayerInfo

class PointsTableAdapter(
    private val data: List<PlayerInfo?>?,
    private val itemClick: OnClickListener
) : RecyclerView.Adapter<PointsTableAdapter.PtsTableViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PtsTableViewHolder {
        val binding =
            ItemPlayerInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PtsTableViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: PtsTableViewHolder, position: Int) {
        data?.get(position)?.let { holder.bindViews(it, itemClick) }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class PtsTableViewHolder(
        private val holder: ItemPlayerInfoBinding,
        private val context: Context
    ) :
        ViewHolder(holder.root) {

        fun bindViews(items: PlayerInfo, onClickListener: OnClickListener) {
            holder.tvPlayerName.text = items.name
            holder.tvPoints.text = items.totalPlay.toString()
            Glide.with(context).load(items.icon).into(holder.ivPlayer)
            itemView.tag = items.id
            holder.root.setOnClickListener {
                onClickListener.onClick(itemView)
            }
        }
    }
}


