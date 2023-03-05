package com.list_of_heroes.ow2companion.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.list_of_heroes.ow2companion.databinding.HeroesItemBinding
import com.list_of_heroes.ow2companion.network.models.AllHeroesItem

class AllHeroesAdapter(private val listener: HeroItemListener) :
    RecyclerView.Adapter<AllHeroesAdapter.AllHeroesViewHolder>() {

    interface HeroItemListener {
        fun onClickedHero(heroName: String)
    }

    var allHeroesList = listOf<AllHeroesItem>()
        set(value) {
            val callback = AllHeroesDiffUtil(allHeroesList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllHeroesViewHolder {
        val binding: HeroesItemBinding = HeroesItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AllHeroesViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = allHeroesList.size

    override fun onBindViewHolder(holder: AllHeroesViewHolder, position: Int) =
        holder.bind(allHeroesList[position])

    inner class AllHeroesViewHolder(
        private val binding: HeroesItemBinding,
        private val listener: HeroItemListener
    ) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private lateinit var heroesItem: AllHeroesItem

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: AllHeroesItem) {
            this.heroesItem = item
            binding.heroesName.text = item.name
            Glide.with(binding.root)
                .load(item.portrait)
                .into(binding.heroesImage)
        }

        override fun onClick(p0: View?) {
            listener.onClickedHero(heroesItem.key)
        }
    }
}