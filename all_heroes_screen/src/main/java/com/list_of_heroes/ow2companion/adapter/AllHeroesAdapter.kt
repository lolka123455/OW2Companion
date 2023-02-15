package com.list_of_heroes.ow2companion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.list_of_heroes.ow2companion.databinding.HeroesItemBinding
import com.list_of_heroes.ow2companion.network.models.AllHeroesItem

class AllHeroesAdapter : RecyclerView.Adapter<AllHeroesAdapter.AllHeroesViewHolder>() {

    var allHeroesList = listOf<AllHeroesItem>()
        set(value) {
            val callback = AllHeroesDiffUtil(allHeroesList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    class AllHeroesViewHolder(view: HeroesItemBinding) : RecyclerView.ViewHolder(view.root) {
        val binding = HeroesItemBinding.bind(view.root)
    }

    override fun onBindViewHolder(holder: AllHeroesViewHolder, position: Int) {
        bind(holder.binding, allHeroesList[position])
    }

    private fun bind(binding: HeroesItemBinding, allHeroesItem: AllHeroesItem) {
        binding.heroesName.text = allHeroesItem.name

        Glide.with(binding.root)
            .load(allHeroesItem.portrait)
            .into(binding.heroesImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AllHeroesViewHolder(
        HeroesItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = allHeroesList.size

}