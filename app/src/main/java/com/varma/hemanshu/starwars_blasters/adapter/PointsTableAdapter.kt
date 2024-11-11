package com.varma.hemanshu.starwars_blasters.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.varma.hemanshu.starwars_blasters.databinding.ItemPlayerInfoBinding
import com.varma.hemanshu.starwars_blasters.model.PlayerInfo

class PointsTableAdapter(
    private val onItemClick: (PlayerInfo) -> Unit
) : ListAdapter<PlayerInfo, PointsTableAdapter.PtsTableViewHolder>(PlayerInfoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PtsTableViewHolder {
        val binding =
            ItemPlayerInfoBinding.inflate(LayoutInflater.from(parent.context))
        return PtsTableViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: PtsTableViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class PtsTableViewHolder(
        private val binding: ItemPlayerInfoBinding,
        private val context: Context
    ) : ViewHolder(binding.root) {

        fun bind(item: PlayerInfo) {
            binding.tvPlayerName.text = item.name
            binding.tvPoints.text = item.totalPlay.toString()
            Glide.with(context).load(item.icon).into(binding.ivPlayer)
            itemView.tag = item.id
            binding.root.setOnClickListener {
                onItemClick(item)
            }
            binding.executePendingBindings()
        }
    }
}

class PlayerInfoDiffCallback : DiffUtil.ItemCallback<PlayerInfo>() {
    override fun areItemsTheSame(oldItem: PlayerInfo, newItem: PlayerInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PlayerInfo, newItem: PlayerInfo): Boolean {
        return oldItem == newItem
    }
}

