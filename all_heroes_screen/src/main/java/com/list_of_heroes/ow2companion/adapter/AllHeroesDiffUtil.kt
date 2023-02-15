package com.list_of_heroes.ow2companion.adapter

import androidx.recyclerview.widget.DiffUtil
import com.list_of_heroes.ow2companion.network.models.AllHeroesItem

class AllHeroesDiffUtil(
    private val oldList: List<AllHeroesItem>,
    private val newList: List<AllHeroesItem>
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