package com.game_modes.ow2companion.adapters.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.game_modes.ow2companion.databinding.FragmentGameModesBinding
import com.game_modes.ow2companion.databinding.GamesModesItemBinding
import com.game_modes.ow2companion.network.models.GameModesItem
import com.squareup.picasso.Picasso

class GameModesViewHolder constructor(
    private val binding: GamesModesItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(gameModesItem: GameModesItem) {
        binding.gameModeTitle.text = gameModesItem.name
        binding.gameModeDescription.text = gameModesItem.description

        Picasso.get()
            .load(gameModesItem.screenshot)
            .into(binding.gameModeImage)
    }
}