package com.allHeroesList.ow2companion.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allHeroesList.ow2companion.R
import com.allHeroesList.ow2companion.databinding.HeroesItemBinding
import com.allHeroesList.ow2companion.network.models.AllHeroesItem
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class AllHeroesAdapterList(
    private var heroesItem: AllHeroesItem,
    private val listener: HeroItemListener
) : AbstractBindingItem<HeroesItemBinding>(), View.OnClickListener {

    interface HeroItemListener {
        fun onClickedHero(heroName: String)
    }

    override val type: Int = R.layout.heroes_item

    override var identifier: Long = heroesItem.hashCode().toLong()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): HeroesItemBinding =
        HeroesItemBinding.inflate(inflater, parent, false)

    override fun bindView(binding: HeroesItemBinding, payloads: List<Any>) {
        binding.heroesName.text = heroesItem.name

        Glide.with(binding.root)
            .load(heroesItem.portrait)
            .into(binding.heroesImage)

        binding.root.setOnClickListener(this)
    }

    override fun unbindView(binding: HeroesItemBinding) {
        super.unbindView(binding)
        binding.heroesName.text = null
        binding.root.setOnClickListener(null)
    }

    override fun onClick(view: View?) {
        listener.onClickedHero(heroesItem.key)
    }
}