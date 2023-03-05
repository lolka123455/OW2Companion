package com.details_for_hero_screen.ow2companion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.details_for_hero_screen.ow2companion.databinding.FragmentDetailsHeroBinding
import com.details_for_hero_screen.ow2companion.network.models.DetailsInfoHero
import com.details_for_hero_screen.ow2companion.viewmodels.DetailsHeroViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    private fun setInitialData(){
        arguments?.getString("key")?.let { viewModel.getDetailsHero(it) }
    }

    private fun observe(){
        observeHeroBasicInformation()
    }

    private fun observeHeroBasicInformation(){
        lifecycleScope.launchWhenStarted {
            viewModel.detailsHero.collect{setHeroBasicInformation(it)}
        }
    }

    // TODO Тут есть проблема что 2 абзац обрывается надо смотреть верстку 2 абзаца или почему не хватило места

    private fun setHeroBasicInformation(detailsInfoHero: DetailsInfoHero?) {

        if (detailsInfoHero == null) return

        with(binding) {
            tvHeroName.text = detailsInfoHero.name
            tvHeroDescription.text = detailsInfoHero.description
            tvHeroRole.text = detailsInfoHero.role
            tvHeroLocation.text = detailsInfoHero.location

            tvHeroSynopsis.text = detailsInfoHero.story.summary

            tvHeroTitleStory1.text = detailsInfoHero.story.chapters[0].title
            tvHeroStory1.text = detailsInfoHero.story.chapters[0].content

            tvHeroTitleStory2.text = detailsInfoHero.story.chapters[1].title
            tvHeroStory2.text = detailsInfoHero.story.chapters[1].content

            // В зависимости от героя , 3 абзаца может и не быть ,потому тут идет проверка и если его нет то он просто не показывается
            if (detailsInfoHero.story.chapters.size == 3) {
                tvHeroTitleStory3.text = detailsInfoHero.story.chapters[2].title
                tvHeroStory3.text = detailsInfoHero.story.chapters[2].content
            } else {
                tvHeroTitleStory3.visibility = View.GONE
                tvHeroStory3.visibility = View.GONE
            }
        }

        Glide.with(binding.root)
            .load(detailsInfoHero.portrait)
            .into(binding.ivHeroImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}