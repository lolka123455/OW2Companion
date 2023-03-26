package com.details_for_hero_screen.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.details_for_hero_screen.ow2companion.databinding.FragmentDetailsHeroBinding
import com.details_for_hero_screen.ow2companion.network.models.DetailsInfoHero
import com.details_for_hero_screen.ow2companion.viewmodels.DetailsHeroViewModel
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
        setInitialData()
        observe()
    }

    private fun setInitialData() {
        arguments?.getString("key")?.let { key ->
            viewModel.getDetailsHero(key)
        } ?: run {
            showErrorMessage("No hero was found!")
        }
    }

    private fun showErrorMessage(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        requireActivity().onNavigateUp()
    }

    private fun observe(){
        observeHeroBasicInformation()
    }

    private fun observeHeroBasicInformation(){
        lifecycleScope.launchWhenStarted {
            viewModel.detailsHero.collect{setHeroBasicInformation(it)}
        }
    }

    private fun setHeroBasicInformation(detailsInfoHero: DetailsInfoHero?) {
        if (detailsInfoHero == null) return
        setHeroNameAndDescription(detailsInfoHero)
        setHeroRoleAndLocation(detailsInfoHero)
        setHeroSynopsis(detailsInfoHero)
        setHeroStory(detailsInfoHero)
        setHeroImage(detailsInfoHero)
    }

    private fun setHeroNameAndDescription(detailsInfoHero: DetailsInfoHero) {
        with(binding) {
            tvHeroName.text = detailsInfoHero.name
            tvHeroDescription.text = detailsInfoHero.description
        }
    }

    private fun setHeroRoleAndLocation(detailsInfoHero: DetailsInfoHero) {
        with(binding) {
            tvHeroRole.text = detailsInfoHero.role
            tvHeroLocation.text = detailsInfoHero.location
        }
    }

    private fun setHeroSynopsis(detailsInfoHero: DetailsInfoHero) {
        with(binding) {
            tvHeroSynopsis.text = detailsInfoHero.story.summary
        }
    }

    private fun setHeroStory(detailsInfoHero: DetailsInfoHero) {
        with(binding) {
            tvHeroTitleStory1.text = detailsInfoHero.story.chapters[0].title
            tvHeroStory1.text = detailsInfoHero.story.chapters[0].content

            tvHeroTitleStory2.text = detailsInfoHero.story.chapters[1].title
            tvHeroStory2.text = detailsInfoHero.story.chapters[1].content

            // check if there are at least 3 chapters in the story
            if (detailsInfoHero.story.chapters.size >= 3) {
                // if there are, show the third chapter title and content
                tvHeroTitleStory3.text = detailsInfoHero.story.chapters[2].title
                tvHeroStory3.text = detailsInfoHero.story.chapters[2].content
            } else {
                // otherwise, hide the third chapter title and content
                tvHeroTitleStory3.visibility = View.GONE
                tvHeroStory3.visibility = View.GONE
            }
        }
    }

    private fun setHeroImage(detailsInfoHero: DetailsInfoHero) {
        Glide.with(binding.root)
            .load(detailsInfoHero.portrait)
            .into(binding.ivHeroImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}