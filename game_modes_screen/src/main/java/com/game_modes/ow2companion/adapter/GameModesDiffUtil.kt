package com.game_modes.ow2companion.adapter

import androidx.recyclerview.widget.DiffUtil
import com.game_modes.ow2companion.network.models.GameModesItem

class GameModesDiffUtil(
    private val oldList: List<GameModesItem>,
    private val newList: List<GameModesItem>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}