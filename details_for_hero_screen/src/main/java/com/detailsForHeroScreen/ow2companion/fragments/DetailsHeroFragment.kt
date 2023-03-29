package com.detailsForHeroScreen.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.detailsForHeroScreen.ow2companion.databinding.FragmentDetailsHeroBinding
import com.detailsForHeroScreen.ow2companion.network.models.DetailsInfoHero
import com.detailsForHeroScreen.ow2companion.viewmodels.DetailsHeroViewModel
import kotlinx.coroutines.flow.filterNotNull
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

    private fun observe() {
        observeHeroBasicInformation()
        observeServerResponse()
    }

    private fun observeHeroBasicInformation() {
        lifecycleScope.launchWhenStarted {
            viewModel.detailsHero.collect { setHeroBasicInformation(it) }
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

    /**
     * Updates the UI with the hero story information.
     *
     * This function updates the text views in the UI with the title and content
     * of each chapter in the hero's story. If there are fewer chapters than text views,
     * the remaining text views are hidden.
     *
     * @param detailsInfoHero The [DetailsInfoHero] object containing the hero's story information.
     */

    private fun setHeroStory(detailsInfoHero: DetailsInfoHero) {
        with(binding) {
            val chapterViews = listOf(
                Pair(tvHeroTitleStory1, tvHeroStory1),
                Pair(tvHeroTitleStory2, tvHeroStory2),
                Pair(tvHeroTitleStory3, tvHeroStory3)
            )

            detailsInfoHero.story.chapters.forEachIndexed { index, chapter ->
                if (index < chapterViews.size) {
                    chapterViews[index].first.text = chapter.title
                    chapterViews[index].second.text = chapter.content
                }
            }

            if (detailsInfoHero.story.chapters.size < chapterViews.size) {
                for (i in detailsInfoHero.story.chapters.size until chapterViews.size) {
                    chapterViews[i].first.visibility = View.GONE
                    chapterViews[i].second.visibility = View.GONE
                }
            }
        }
    }

    private fun setHeroImage(detailsInfoHero: DetailsInfoHero) {
        Glide.with(binding.root)
            .load(detailsInfoHero.portrait)
            .into(binding.ivHeroImage)
    }

    private fun observeServerResponse() {
        lifecycleScope.launchWhenStarted {
            viewModel.serverResponse
                .filterNotNull()
                .collect { response ->
                    displayServerResponse(response)
                    viewModel.clearServerResponse()
                }
        }
    }

    private fun displayServerResponse(response: String) {
        Toast.makeText(requireContext(), response, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}