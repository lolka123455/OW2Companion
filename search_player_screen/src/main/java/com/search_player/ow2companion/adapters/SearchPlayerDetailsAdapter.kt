package com.search_player.ow2companion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.search_player.ow2companion.R
import com.search_player.ow2companion.databinding.SearchPlayerDetailsItemBinding
import com.search_player.ow2companion.network.models.SearchPlayer

class SearchPlayerDetailsAdapter(private val listener: PlayerItemListener) :
    RecyclerView.Adapter<SearchPlayerDetailsAdapter.AllHeroesViewHolder>() {

    interface PlayerItemListener {
        fun onClickedHero(playerName: String)
    }

    var allHeroesList = listOf<SearchPlayer>()
        set(value) {
            val callback = SearchPLayerDetailsDiffUtil(allHeroesList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllHeroesViewHolder {
        val binding: SearchPlayerDetailsItemBinding = SearchPlayerDetailsItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AllHeroesViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = allHeroesList.size

    override fun onBindViewHolder(holder: AllHeroesViewHolder, position: Int) =
        holder.bind(allHeroesList[position])

    inner class AllHeroesViewHolder(
        private val binding: SearchPlayerDetailsItemBinding,
        private val listener: PlayerItemListener
    ) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private lateinit var playerItem: SearchPlayer

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: SearchPlayer) {
            this.playerItem = item
            binding.playerUsername.text = item.username
            binding.playerTitle.text = item.title

            Glide.with(binding.root)
                .load(item.avatar)
                .into(binding.playerAvatar)

            when (item.endorsement.level) {
                1 -> Glide.with(binding.root)
                    .load(R.drawable.ic_1_lvl_rep)
                    .into(binding.ivLvlReputation)
                2 -> Glide.with(binding.root)
                    .load(R.drawable.ic_2_lvl_rep)
                    .into(binding.ivLvlReputation)
                3 -> Glide.with(binding.root)
                    .load(R.drawable.ic_3_lvl_rep)
                    .into(binding.ivLvlReputation)
                4 -> Glide.with(binding.root)
                    .load(R.drawable.ic_4_lvl_rep)
                    .into(binding.ivLvlReputation)
                else -> binding.ivLvlReputation.visibility = View.GONE
            }
        }

        override fun onClick(p0: View?) {
            listener.onClickedHero(playerItem.username)
        }
    }
}