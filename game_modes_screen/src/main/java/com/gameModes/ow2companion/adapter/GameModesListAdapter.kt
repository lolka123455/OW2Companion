package com.gameModes.ow2companion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.gameModes.ow2companion.R
import com.gameModes.ow2companion.databinding.GamesModesItemBinding
import com.gameModes.ow2companion.network.models.GameModesItem
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class GameModesListAdapter(
    private var gameModesItem: GameModesItem
) : AbstractBindingItem<GamesModesItemBinding>() {

    override val type: Int = R.layout.games_modes_item

    override var identifier: Long = gameModesItem.hashCode().toLong()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): GamesModesItemBinding =
        GamesModesItemBinding.inflate(inflater, parent, false)

    override fun bindView(binding: GamesModesItemBinding, payloads: List<Any>) {
        binding.gameModeDescription.text = gameModesItem.description
        binding.gameModeTitle.text = gameModesItem.name

        Glide.with(binding.root)
            .load(gameModesItem.screenshot)
            .into(binding.gameModeImage)
    }

    override fun unbindView(binding: GamesModesItemBinding) {
        super.unbindView(binding)
        binding.gameModeDescription.text = null
        binding.gameModeTitle.text = null
    }
}