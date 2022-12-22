package com.game_modes.ow2companion.adapters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.game_modes.ow2companion.adapters.viewHolder.GameModesViewHolder
import com.game_modes.ow2companion.databinding.GamesModesItemBinding
import com.game_modes.ow2companion.network.models.GameModesItem

class GameModesAdapter constructor(private val gameModesList: List<GameModesItem>) :
    RecyclerView.Adapter<GameModesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameModesViewHolder {
        return GameModesViewHolder(
            GamesModesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameModesViewHolder, position: Int) {
        holder.bind(gameModesList[position])
    }

    override fun getItemCount(): Int {
        return gameModesList.size
    }
}