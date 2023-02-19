package com.details_for_hero_screen.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.details_for_hero_screen.ow2companion.databinding.FragmentDetailsHeroBinding
import com.details_for_hero_screen.ow2companion.network.models.DetailsInfoHero
import com.details_for_hero_screen.ow2companion.viewmodels.DetailsHeroViewModel
import com.list_of_heroes.ow2companion.fragments.KEY_FRAGMENT_HEROES
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsHeroFragment : Fragment() {

    private val binding: FragmentDetailsHeroBinding
        get() = _binding!!
    private var _binding: FragmentDetailsHeroBinding? = null
    private val viewModel: DetailsHeroViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsHeroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailsInfoHero = arguments?.get(KEY_FRAGMENT_HEROES) as DetailsInfoHero
        setHeroBasicInformation(detailsInfoHero)
        setHeroStory(detailsInfoHero)
    }

    private fun setHeroBasicInformation(detailsInfoHero: DetailsInfoHero) {
        binding.tvHeroName.text = detailsInfoHero.name
        binding.tvHeroDescription.text = detailsInfoHero.description
        binding.tvHeroRole.text = detailsInfoHero.role
        binding.tvHeroLocation.text = detailsInfoHero.location

        Glide.with(binding.root)
            .load(detailsInfoHero.portrait)
            .into(binding.ivHeroImage)
    }

    private fun setHeroStory(detailsInfoHero: DetailsInfoHero) {
        binding.tvHeroSynopsis.text = detailsInfoHero.story.summary
        binding.tvHeroTitleStory1.text = detailsInfoHero.story.chapters[0].title
        binding.tvHeroStory1.text = detailsInfoHero.story.chapters[0].content
        binding.tvHeroTitleStory2.text = detailsInfoHero.story.chapters[1].title
        binding.tvHeroStory2.text = detailsInfoHero.story.chapters[1].content
        binding.tvHeroTitleStory3.text = detailsInfoHero.story.chapters[2].title
        binding.tvHeroStory3.text = detailsInfoHero.story.chapters[2].content
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}