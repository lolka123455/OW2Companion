package com.game_modes.ow2companion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.game_modes.ow2companion.databinding.GamesModesItemBinding
import com.game_modes.ow2companion.network.models.GameModesItem

class GameModesListAdapter : RecyclerView.Adapter<GameModesListAdapter.GameModesViewHolder>() {

    var gameModesList = listOf<GameModesItem>()
    set(value) {
        val callback = GameModesDiffUtil(gameModesList, value)
        val diffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)
        field = value
    }

    class GameModesViewHolder(view: GamesModesItemBinding) : RecyclerView.ViewHolder(view.root) {
        val binding = GamesModesItemBinding.bind(view.root)
    }

    override fun onBindViewHolder(holder: GameModesViewHolder, position: Int) {
        bind(holder.binding, gameModesList[position])
    }

    private fun bind(binding: GamesModesItemBinding, gameModesItem: GameModesItem) {
        binding.gameModeDescription.text = gameModesItem.description
        binding.gameModeTitle.text = gameModesItem.name


        Glide.with(binding.root)
            .load(gameModesItem.screenshot)
            .into(binding.gameModeImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GameModesViewHolder(
        GamesModesItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = gameModesList.size

}