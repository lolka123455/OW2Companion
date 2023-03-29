package com.searchPlayer.ow2companion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.searchPlayer.ow2companion.R
import com.searchPlayer.ow2companion.databinding.SearchPlayerDetailsItemBinding
import com.searchPlayer.ow2companion.network.models.SearchPlayer

class SearchPlayerDetailsAdapter(private val listener: PlayerItemListener) :
    RecyclerView.Adapter<SearchPlayerDetailsAdapter.AllHeroesViewHolder>() {

    interface PlayerItemListener {
        fun onClickedPlayer(playerName: String)
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
            LayoutInflater.from(parent.context),
            parent,
            false
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

        private fun loadGlideImage(url: Any?, imageView: ImageView) {
            Glide.with(binding.root)
                .load(url)
                .into(imageView)
        }

        fun bind(item: SearchPlayer) {
            playerItem = item
            with(binding) {
                playerUsername.text = item.username
                playerTitle.text = item.title
                loadGlideImage(item.avatar, playerAvatar)

                if (item.privacy == "public") {
                    loadGlideImage(getReputationIcon(item.endorsement.level), ivLvlReputation)
                    loadGlideImage(item.competitive.pc.tank.rank_icon, ivTankRank)
                    loadGlideImage(item.competitive.pc.damage.rank_icon, ivDamageRank)
                    loadGlideImage(item.competitive.pc.support.rank_icon, ivSupportRank)
                } else {
                    setRankIconsVisibility()
                    ivLvlReputation.visibility = View.GONE
                    root.setOnClickListener(null)
                    Toast.makeText(root.context, "This player is private", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        private fun getReputationIcon(level: Int): Int? {
            return when (level) {
                1 -> R.drawable.ic_1_lvl_rep
                2 -> R.drawable.ic_2_lvl_rep
                3 -> R.drawable.ic_3_lvl_rep
                4 -> R.drawable.ic_4_lvl_rep
                else -> null
            }
        }

        private fun setRankIconsVisibility() {
            with(binding) {
                ivTankRank.visibility = View.GONE
                ivDamageRank.visibility = View.GONE
                ivSupportRank.visibility = View.GONE
            }
        }

        override fun onClick(p0: View?) {
            listener.onClickedPlayer(playerItem.username)
        }
    }
}