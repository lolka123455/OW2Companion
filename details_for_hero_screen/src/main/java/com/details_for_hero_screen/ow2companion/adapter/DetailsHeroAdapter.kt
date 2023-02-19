package com.details_for_hero_screen.ow2companion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.details_for_hero_screen.ow2companion.R
import com.details_for_hero_screen.ow2companion.databinding.FragmentDetailsHeroBinding
import com.details_for_hero_screen.ow2companion.network.models.DetailsInfoHero
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class DetailsHeroAdapter(
    private var detailsHeroAdapter: DetailsInfoHero
) : AbstractBindingItem<FragmentDetailsHeroBinding>() {

    override val type: Int = R.layout.fragment_details_hero

    override var identifier: Long = detailsHeroAdapter.hashCode().toLong()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): FragmentDetailsHeroBinding =
        FragmentDetailsHeroBinding.inflate(inflater, parent, false)

    override fun bindView(binding: FragmentDetailsHeroBinding, payloads: List<Any>) {
        binding.tvHeroName.text = detailsHeroAdapter.name
        binding.tvHeroDescription.text = detailsHeroAdapter.description
        binding.tvHeroRole.text = detailsHeroAdapter.role
        binding.tvHeroLocation.text = detailsHeroAdapter.location

        binding.tvHeroSynopsis.text = detailsHeroAdapter.story.summary

        binding.tvHeroTitleStory1.text = detailsHeroAdapter.story.chapters[0].title
        binding.tvHeroStory1.text = detailsHeroAdapter.story.chapters[0].content

        binding.tvHeroTitleStory2.text = detailsHeroAdapter.story.chapters[1].title
        binding.tvHeroStory2.text = detailsHeroAdapter.story.chapters[1].content

        binding.tvHeroTitleStory3.text = detailsHeroAdapter.story.chapters[2].title
        binding.tvHeroStory3.text = detailsHeroAdapter.story.chapters[2].content


        Glide.with(binding.root)
            .load(detailsHeroAdapter.portrait)
            .into(binding.ivHeroImage)
    }
}