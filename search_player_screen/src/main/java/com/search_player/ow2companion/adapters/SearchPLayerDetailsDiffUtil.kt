package com.search_player.ow2companion.adapters

import androidx.recyclerview.widget.DiffUtil
import com.search_player.ow2companion.network.models.SearchPlayer

class SearchPLayerDetailsDiffUtil(
    private val oldList: List<SearchPlayer>,
    private val newList: List<SearchPlayer>
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